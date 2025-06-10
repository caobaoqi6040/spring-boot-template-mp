package dev.caobaoqi.backend.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfiguration {

	public static final String DATE_FORMATTER_PATTERN = "YYYY-MM-dd HH:mm:ss";
	public static final String LOCAL_TIME_FORMATTER_PATTERN = "HH:mm:ss";
	public static final String LOCAL_DATE_FORMATTER_PATTERN = "YYYY-MM-dd";
	public static final String LOCAL_DATE_TIME_FORMATTER_PATTERN = "YYYY-MM-dd HH:mm:ss";

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_TIME_FORMATTER_PATTERN)));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)));
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMATTER_PATTERN)));
		mapper.registerModule(new ParameterNamesModule())
			.registerModule(new Jdk8Module())
			.registerModule(javaTimeModule);
		mapper.setDateFormat(new SimpleDateFormat(DATE_FORMATTER_PATTERN));
		return mapper;
	}

}
