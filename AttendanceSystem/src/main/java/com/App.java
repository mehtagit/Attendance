package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan("com")
@EnableAutoConfiguration
@ImportResource("classpath:spring-servlet.xml")
public class App 
{
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args); 
    }
}
