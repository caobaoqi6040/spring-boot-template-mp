package dev.caobaoqi.backend.config.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfiguration {

	public static final String REDIS_VERIFY_CODE = "verify::code";
	public static final String REDIS_LOCK_TIME = "lock::time";

	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
		Map<String, RedisCacheConfiguration> redisCacheConfigurations = new HashMap<>();
		// config verify code
		redisCacheConfigurations.put(
			REDIS_VERIFY_CODE,
			RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(1))
				.disableCachingNullValues());
		// config lock time
		redisCacheConfigurations.put(
			REDIS_LOCK_TIME,
			RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(3))
				.disableCachingNullValues());
		return RedisCacheManager.builder(connectionFactory)
			.withInitialCacheConfigurations(redisCacheConfigurations)
			.build();
	}


}
