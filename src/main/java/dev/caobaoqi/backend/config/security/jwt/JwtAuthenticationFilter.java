package dev.caobaoqi.backend.config.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.caobaoqi.backend.config.security.RequestLogFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Setter
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final Jwt jwt;

	@SuppressWarnings("NullableProblems")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = jwt.extract(request);
		DecodedJWT decodedJWT = jwt.resolve(token);
		if (StringUtils.isNotEmpty(token) && ObjectUtils.allNotNull(decodedJWT)) {
			try {
				if (jwt.isDeleted(decodedJWT.getId())) {
					log.warn("token deleted(black list)");
					filterChain.doFilter(request, response);
					return;
				}
				UserDetails userDetails = jwt.toUser(decodedJWT);
				JwtAuthenticationToken authenticated = JwtAuthenticationToken.authenticated(userDetails, token, userDetails.getAuthorities());
				authenticated.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticated);
				request.setAttribute(RequestLogFilter.ATTR_USER_EMAIL, jwt.getUserEmail(decodedJWT));
			} catch (Exception e) {
				log.error("jwt with invalid user id ", e);
			}
		}
		filterChain.doFilter(request, response);
	}
}
