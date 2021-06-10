package com.mahzarasua.resumeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class ResumeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeApiApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/v1/resume/*"))
				.apis(RequestHandlerSelectors.basePackage("com.mahzarasua"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Resume API",
				"List of all Resumes stored",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Miguel Hernandez", "https://githug.com/mahzarasua", "mahzarasua@outlook.com"),
				"API License",
				"https://github.com/mahzarasua",
				Collections.emptyList()
		);
	}
}
