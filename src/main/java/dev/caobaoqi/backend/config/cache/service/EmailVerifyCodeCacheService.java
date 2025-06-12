package dev.caobaoqi.backend.config.cache.service;

import dev.caobaoqi.backend.config.cache.CacheConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailVerifyCodeCacheService {

	@Cacheable(value = CacheConfiguration.EMAIL_VERIFY_CODE, key = "{#identify}", unless = "#result == null")
	public String getVerifyCodeBy(String identify) {
		return null;
	}

	@CachePut(value = CacheConfiguration.EMAIL_VERIFY_CODE, key = "{#identify}")
	public String upsertVerifyCodeBy(String identify, String value) {
		return value;
	}

	@CacheEvict(value = CacheConfiguration.EMAIL_VERIFY_CODE, key = "{#identify}")
	public void removeVerifyCodeBy(String identify) {
	}

	@CacheEvict(value = CacheConfiguration.EMAIL_VERIFY_CODE, allEntries = true)
	public void clearAllVerifyCode() {
	}

}
