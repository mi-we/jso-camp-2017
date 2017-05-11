package com.cbo.springmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by cbo on 10.05.17.
 */
@SpringBootApplication
@EnableSwagger2
@Configuration
public class Application {
  public static void main(String ... args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Docket newsApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring REST Sample with Swagger")
        .description("Spring REST Sample with Swagger")
        .termsOfServiceUrl("http://localhost:8080/swagger")
        .contact("Ch. Bonnhoff")
        .license("Apache License Version 2.0")
        .version("2.0")
        .build();
  }
}
