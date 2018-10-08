package com.silalahi.valentinus.app;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BackendBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendBankApplication.class, args);
	}

	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Aplikasi Bank", "API yang menyediakan layanan perbankan", "API TOS", "Terms of service",
				new Contact("Valentinus Silalahi", "valentinusilalahi.github.io", "valentinus.silalahi@gmail.com"),
				"Apache License 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}
