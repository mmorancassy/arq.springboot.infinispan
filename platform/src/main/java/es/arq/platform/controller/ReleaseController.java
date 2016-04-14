package es.arq.platform.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.arq.persistence.provider.DatabaseProvider;
import es.arq.persistence.provider.exceptions.PersistenceException;

@Controller
@EnableAutoConfiguration
@ComponentScan("es.arq.*")
public class ReleaseController {

	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(ReleaseController.class);
	
	@Autowired
	private DatabaseProvider mongoProvider;		
	
    @RequestMapping("/")
    @ResponseBody
    String home() {
    	try {
			Map<String, String> results = mongoProvider.query("", "Regions",  100);
			
			results.forEach((id, doc) -> LOG.info("Retrieve document: " + doc + " with id: " + id));
			
			return "Hello World!";
		} catch (PersistenceException e) {
			return "500";
		}
    }

    // TODO externalizar a un módulo 'server'
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReleaseController.class, args);
    }
}

