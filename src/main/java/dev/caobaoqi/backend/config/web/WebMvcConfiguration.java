package dev.caobaoqi.backend.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

import java.util.Arrays;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Value("${cors.allowed-origins}")
	private String allowedOrigins;
	@Value("${cors.allowed-methods}")
	private String allowedMethods;
	@Value("${cors.allowed-headers}")
	private String allowedHeaders;
	@Value("${cors.allowed-expose-headers}")
	private String allowedExposeHeaders;

	@Value("${static-resource.handler}")
	private String resourceHandler;

	@Value("${static-resource.locations}")
	private String resourceLocations;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
		configuration.setAllowedMethods(Arrays.asList(allowedMethods.split(",")));
		configuration.setAllowedHeaders(Arrays.asList(allowedHeaders.split(",")));
		configuration.setExposedHeaders(Arrays.asList(allowedExposeHeaders.split(",")));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler(resourceHandler)
			.addResourceLocations(resourceLocations)
			.setCachePeriod(60)
			.resourceChain(true)
			.addResolver(new EncodedResourceResolver());
	}
}
