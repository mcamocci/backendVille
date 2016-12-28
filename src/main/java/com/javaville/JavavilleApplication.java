package com.javaville;

import jdk.nashorn.internal.objects.Global;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class JavavilleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JavavilleApplication.class, args);
	}
        
       
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application
                    .parent(Global.class)
                    .sources(JavavilleApplication.class)
                    .profiles("container")
                    ;
        }
}
