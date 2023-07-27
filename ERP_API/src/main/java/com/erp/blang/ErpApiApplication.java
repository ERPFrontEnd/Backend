package com.erp.blang;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
public class ErpApiApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ErpApiApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override            
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
//            }
//        };
//    }

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.versatile.erp")).paths(PathSelectors.any()).build()
				.apiInfo(getInfo());
	}

	private ApiInfo getInfo() {
		return new ApiInfo(
				"Versatile-ERP", "By Versatile Commerce", "V1", "terms of Service", new Contact("HKP",
						"https://www.investopedia.com/terms/a/accountspayable.asp", "hariofficial137@gmail.com"),
				"No Licenses", "No Licenses URl", Collections.emptyList());

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


}
