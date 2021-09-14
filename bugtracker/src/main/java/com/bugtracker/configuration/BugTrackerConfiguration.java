package com.bugtracker.configuration;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.*;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class BugTrackerConfiguration {

	@Bean(name = "mapper")
	public ModelMapper getMapper() {
		
		return new ModelMapper();
	}
	
	 @Bean
	    public Docket api(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(paths())
	                .build()
	                .apiInfo(apiInfo());
	    }

	    public Predicate<String> paths(){
	        return input -> !input.contains("/error");
	    }

	    public ApiInfo apiInfo(){
	        return new ApiInfoBuilder()
	                .title("Bug Tracking Application")
	                .description("Bug Tracking Application")
	                .version("1.0")
	                .build();
	    }
}
