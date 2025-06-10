package dev.caobaoqi.backend;

import org.springframework.boot.SpringApplication;

public class TestSpringBootTemplateMpApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootTemplateMpApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
