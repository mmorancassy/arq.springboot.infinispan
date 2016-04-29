package es.arq.platform.services;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import es.arq.platform.controller.dto.DiscogsRelease;

@Service
public class DiscogsService {
	
	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(DiscogsService.class);
	
	// TODO Endpoints, externalizar a properties
	private static final String GET_COLLECTION_BY_USER = "https://api.discogs.com/users/<userid>/collection/folders/0/releases?page=1&per_page=1";
	//private static final String GET_COLLECTION_BY_USER = "https://api.discogs.com/users/<userid>/collection/folders/0";
	
	private SimpleClientHttpRequestFactory setProxy() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        InetSocketAddress address = new InetSocketAddress("proxycorp.geci", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
        factory.setProxy(proxy);
        
        Authenticator.setDefault(new Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication("T0000104", "12345678".toCharArray());
        	}
        });
        
        return factory;
	}
	
	@HystrixCommand(fallbackMethod="retrieveFallbackFindCollectionByUser")
	public List<String> findCollectionByUser(String userId) {
		List<String> userCollection =  new ArrayList<String>();
		String endpoint = GET_COLLECTION_BY_USER.replace("<userid>", userId);
		
		// GET Request
		RestTemplate restTemplate = new RestTemplate(setProxy());
		
		LOG.info("Invoking service endpoint... " + endpoint);
		
		ResponseEntity<DiscogsRelease> releases = restTemplate.getForEntity(endpoint, DiscogsRelease.class);
		
		String documents = releases.getBody().toString();
		
		LOG.info("Objects retrieved from database: " + documents);
		
		userCollection.add(documents);
		
		return userCollection;
	}
	
	public List<String> retrieveFallbackFindCollectionByUser(String userId) {
		return new ArrayList<String>();
	}

}
