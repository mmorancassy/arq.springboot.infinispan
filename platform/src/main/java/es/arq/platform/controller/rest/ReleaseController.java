package es.arq.platform.controller.rest;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.arq.persistence.provider.DatabaseProvider;
import es.arq.persistence.provider.exceptions.PersistenceException;

@Api(name = "Releases services", 
	 description = "Methods for managing Discogs personal collection", 
	 group = "Music", 
	 visibility = ApiVisibility.PUBLIC, 
	 stage = ApiStage.ALPHA)
@RestController
@RequestMapping(value="/releases", produces=MediaType.APPLICATION_JSON_VALUE)
public class ReleaseController {

	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(ReleaseController.class);
	
	@Autowired
	private DatabaseProvider mongoProvider;		
	
	@ApiMethod
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public @ApiResponseObject ResponseEntity<Object> find(@PathVariable String id) {
    	String document = null;
    	try {
    		document = mongoProvider.getById("garbage", id);
			
			return new ResponseEntity<Object>(document, HttpStatus.OK);
			
		} catch (PersistenceException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
    }
    
	@ApiMethod
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public @ApiResponseObject ResponseEntity<Object> findAll() {
    	List<String> documents = null;
    	try {
    		// TODO add RequestParam for limit
    		documents = mongoProvider.query("", "garbage", 100);
			
    		return new ResponseEntity<Object>(documents, HttpStatus.OK);
		} catch (PersistenceException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
    }

}

