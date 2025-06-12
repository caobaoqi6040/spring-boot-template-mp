package dev.caobaoqi.backend.config.cache.service;

import dev.caobaoqi.backend.config.cache.CacheConfiguration;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenCacheService {


	@Cacheable(value = CacheConfiguration.JWT_TOKEN, key = "{#identify}", unless = "#result == null")
	public String getCache(String identify) {
		return null;
	}

	@CachePut(value = CacheConfiguration.JWT_TOKEN, key = "{#identify}")
	public String setCache(String identify, String value) {
		return value;
	}

	@CacheEvict(value = CacheConfiguration.JWT_TOKEN, key = "{#identify}")
	public void removeById(String identify) {
	}

	@CacheEvict(value = CacheConfiguration.JWT_TOKEN, allEntries = true)
	public void removeAll() {
	}
}
