package es.arq.platform.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {				
		String frontend = "classpath:/";
		String backend = "classpath:/META-INF/resources/";
		
		// Local environments take files directly from front folder
		if (environment.acceptsProfiles("local")) {
			String workingDirectory = System.getProperty("user.dir");
			
			if (workingDirectory.endsWith("/platform")) {
				workingDirectory = workingDirectory.substring(0, workingDirectory.lastIndexOf("/platform"));
			}			

			frontend = "file:///" + workingDirectory + "/front/src/main/";
			
			System.out.println("WebMvcConfig.addResourceHandlers() --> " + frontend);
		}			
		
		registry.addResourceHandler("/**").addResourceLocations(frontend, backend);
	}

}
