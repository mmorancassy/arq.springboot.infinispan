package es.arq.platform.application;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableEurekaClient 
@EnableCircuitBreaker
@EnableJSONDoc
@ComponentScan("es.arq.*")
public class WebApplication {
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
    
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
      TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
          @Override
          protected void postProcessContext(Context context) {
            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setUserConstraint("CONFIDENTIAL");
            SecurityCollection collection = new SecurityCollection();
            collection.addPattern("/*");
            securityConstraint.addCollection(collection);
            context.addConstraint(securityConstraint);
          }
        };
      
      tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
      return tomcat;
    }
    
    /*
     * Redirect http connection to https
     */
    private Connector initiateHttpConnector() {
      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
      connector.setScheme("http");
      connector.setPort(8080);
      connector.setSecure(false);
      connector.setRedirectPort(8443);
      
      return connector;
    }    

}
