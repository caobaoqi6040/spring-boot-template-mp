package dev.caobaoqi.backend.config.security;

import dev.caobaoqi.backend.config.security.jwt.JwtAuthenticationFilter;
import dev.caobaoqi.backend.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

	private final CorsConfigurationSource corsConfigurationSource;
	private final RequestLogFilter requestLogFilter;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public FilterRegistrationBean<RequestLogFilter> requestLogFilterFilterRegistrationBean(RequestLogFilter filter) {
		FilterRegistrationBean<RequestLogFilter> registration = new FilterRegistrationBean<>(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterFilterRegistrationBean(JwtAuthenticationFilter filter) {
		FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthorizationEventPublisher authorizationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		return new SpringAuthorizationEventPublisher(applicationEventPublisher);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		var restfulAuthenticationEntryPointHandler = new RestfulAuthenticationEntryPointHandler();
		http.csrf(AbstractHttpConfigurer::disable);
		http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource));
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.POST, "/api/v1/auth/sign-in").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/v1/auth/sign-up").permitAll()
			.requestMatchers("/api/v1/users/**").hasRole(Role.ADMIN.name())
			.anyRequest().authenticated());
		http.exceptionHandling((exceptionHandling) -> exceptionHandling
			.accessDeniedHandler(restfulAuthenticationEntryPointHandler)
			.authenticationEntryPoint(restfulAuthenticationEntryPointHandler));
		http.addFilterBefore(requestLogFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtAuthenticationFilter, RequestLogFilter.class);
		return http.build();
	}

}
