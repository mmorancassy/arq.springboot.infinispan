package es.arq.platform.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import es.arq.persistence.provider.DatabaseProvider;
import es.arq.persistence.provider.exceptions.PersistenceException;

@Service
public class ReleaseService {
	
	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(ReleaseService.class);
	
	// Release collection
	private static final String RELEASE_COLLECTION = "releases";
	
	@Autowired
	private DiscogsService discogsService;
	
	@Autowired
	private DatabaseProvider mongoProvider;	
	
	@HystrixCommand(fallbackMethod="retrieveFallbackFindById")
	public String findById(String id) throws PersistenceException {
		return mongoProvider.getById(RELEASE_COLLECTION, id);
	}
	
	public String retrieveFallbackFindById(String id) {
		return "{ \"error\" : \"Se ha producido algún error al realizar la operación findById con id: " + id + "\"}";
	}
	
	@HystrixCommand(fallbackMethod="retrieveFallbackFindAll")
	public List<String> findAll() throws PersistenceException {
		return mongoProvider.query("", RELEASE_COLLECTION, 100);
	}
	
	public List<String> retrieveFallbackFindAll()  {
		return new ArrayList<String>(Arrays.asList(new String[]{"{ \"error\" : \"Se ha producido algún error al realizar la operación findAll\"}"}));
	}
	
	@HystrixCommand(fallbackMethod="retrieveFallbackInsert")
	public String insert(String document) throws PersistenceException {
		return mongoProvider.insert(document, RELEASE_COLLECTION);
	}
	
	public String retrieveFallbackInsert(String document) {
		return "{ \"error\" : \"Se ha producido algún error al realizar la operación insert del documento : " + document + "\"}";
	}
	
	@HystrixCommand(fallbackMethod="retrieveFallbackUpdate")
	public String update(String document) throws PersistenceException {
		return mongoProvider.update(document, RELEASE_COLLECTION);	
	}
	
	public String retrieveFallbackUpdate(String document) {
		return "{ \"error\" : \"Se ha producido algún error al realizar la operación update del documento : " + document + "\"}";
	}
	
	@HystrixCommand(fallbackMethod="retrieveFallbackDelete")
	public boolean delete(String documentId) throws PersistenceException {
		return mongoProvider.delete(documentId, RELEASE_COLLECTION);
	}
	
	public boolean retrieveFallbackDelete(String documentId) {
		return false;
	}

}
