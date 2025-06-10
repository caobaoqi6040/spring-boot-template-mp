package dev.caobaoqi.backend.config.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;
import java.util.Objects;

@Configuration
public class I18nConfiguration {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
			new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setAlwaysUseMessageFormat(false);
		messageSource.setFallbackToSystemLocale(false);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultLocale(Locale.US);
		messageSource.setCacheSeconds(3600); // Cache for an hour
		return messageSource;
	}

	@SuppressWarnings("NullableProblems")
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver =
			new AcceptHeaderLocaleResolver() {
				@Override
				public Locale resolveLocale(HttpServletRequest request) {
					String locale = request.getParameter("lang");
					return locale != null
						? Objects.requireNonNull(StringUtils.parseLocaleString(locale))
						: super.resolveLocale(request);
				}
			};
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
}
