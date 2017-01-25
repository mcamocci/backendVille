package com.javaville;

import jdk.nashorn.internal.objects.Global;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class JavavilleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JavavilleApplication.class, args);
	}
        
        /*
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JavavilleApplication.class);
        }*/
}
