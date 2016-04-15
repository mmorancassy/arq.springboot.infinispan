package es.arq.platform.application;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration 
@EnableAutoConfiguration 
@EnableJSONDoc
@ComponentScan("es.arq.*")
public class WebApplication {
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }

}
