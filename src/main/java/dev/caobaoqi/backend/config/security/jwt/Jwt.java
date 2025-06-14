package dev.caobaoqi.backend.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.caobaoqi.backend.config.cache.CacheConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@Getter
public class Jwt {

	private final StringRedisTemplate template;

	/**
	 * 密钥
	 */
	private final String secret;
	/**
	 * 过期时间 单位 h
	 */
	private final int expire;
	private final JWTVerifier verifier;

	public Jwt(
		StringRedisTemplate template, @Value("${jwt.secret}") String secret, @Value("${jwt.expire}") int expire) {
		this.template = template;
		this.verifier = JWT.require(Algorithm.HMAC256(secret)).build();
		this.secret = secret;
		this.expire = expire;
	}

	public String create(UserDetails user, String email) {
		return JWT.create()
			.withJWTId(UUID.randomUUID().toString())
			.withClaim("email", email)
			.withClaim("name", user.getUsername())
			.withClaim("authorities", user.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority).toList())
			.withIssuedAt(new Date())
			.withExpiresAt(
				Date.from(
					LocalDateTime.now()
						.plusHours(expire)
						.atZone(ZoneId.systemDefault())
						.toInstant()))
			.sign(Algorithm.HMAC256(secret));
	}

	public DecodedJWT resolve(String token) {
		if (token == null) return null;
		try {
			DecodedJWT verify = verifier.verify(token);
			Map<String, Claim> claims = verify.getClaims();
			return new Date().after(claims.get("exp").asDate()) ? null : verify;
		} catch (JWTVerificationException e) {
			return null;
		}
	}

	public boolean invalidate(String token) {
		if (token == null) return Boolean.FALSE;
		DecodedJWT verify = verifier.verify(token);
		return deleteToken(verify.getId(), verify.getExpiresAt());
	}

	public UserDetails toUser(DecodedJWT jwt) {
		Map<String, Claim> claims = jwt.getClaims();
		return User
			.withUsername(claims.get("name").asString())
			.password("******")
			.authorities(claims.get("authorities").asArray(String.class))
			.build();
	}

	public String getUserEmail(DecodedJWT jwt) {
		Map<String, Claim> claims = jwt.getClaims();
		return claims.get("email").asString();
	}

	public String extract(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (StringUtils.isNotEmpty(authorization) && authorization.startsWith("Bearer")) {
			return authorization.substring(7);
		} else {
			return null;
		}
	}

	/**
	 * 将 Token 列入 Redis 黑名单中
	 *
	 * @param uuid 令牌ID
	 * @param time 过期时间
	 * @return 是否操作成功
	 */
	private boolean deleteToken(String uuid, Date time) {
		if (this.isDeleted(uuid))
			return false;
		long expire = Math.max(time.getTime() - new Date().getTime(), 0);
		template.opsForValue().set(CacheConfiguration.JWT_TOKEN + "::" + uuid, "", expire, TimeUnit.MILLISECONDS);
		return true;
	}

	/**
	 * 验证 Token 是否被列入 Redis 黑名单
	 *
	 * @param uuid 令牌ID
	 * @return 是否操作成功
	 */
	public boolean isDeleted(String uuid) {
		return template.hasKey(CacheConfiguration.JWT_TOKEN + "::" + uuid);
	}

}
