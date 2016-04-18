package es.arq.platform.controller.rest;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiError;
import org.jsondoc.core.annotation.ApiErrors;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.arq.persistence.provider.DatabaseProvider;
import es.arq.persistence.provider.exceptions.PersistenceException;
import es.arq.platform.controller.dto.Release;

@Api(name = "Music Releases service", 
	 description = "Methods for managing Discogs personal collection", 
	 visibility = ApiVisibility.PUBLIC, 
	 stage = ApiStage.ALPHA)
@RestController
@RequestMapping(value="/api/v1/releases", produces=MediaType.APPLICATION_JSON_VALUE)
public class ReleaseController {

	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(ReleaseController.class);
	
	@Autowired
	private DatabaseProvider mongoProvider;		
	
	@ApiMethod(description="Devuelve un documento con la información relativa a un disco", 
			   verb=ApiVerb.GET,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="200", description="OK"),
						  @ApiError(code="500", description="Internal Server Error")})
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public @ApiResponseObject ResponseEntity<Object> find(@ApiPathParam(name="id", format="String", description="ID del objeto a recuperar de la base de datos") 
    													  @PathVariable String id) {
    	String document = null;
    	try {
    		document = mongoProvider.getById("garbage", id);			
			return new ResponseEntity<Object>(document, HttpStatus.OK);
			
		} catch (PersistenceException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
    }
    
	@ApiMethod(description="Devuelve todos los documentos existentes en la colección de discos", 
			   verb=ApiVerb.GET,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="200", description="OK"),
			  			  @ApiError(code="500", description="Internal Server Error")})	
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
	
	@ApiMethod(description="Inserta un nuevo disco en la base de datos", 
			   verb=ApiVerb.POST,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="200", description="OK"),
			  			  @ApiError(code="500", description="Internal Server Error")})	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public @ApiResponseObject ResponseEntity<Object> insert(@ApiBodyObject @RequestBody Release release) {
		LOG.info("Document to insert in collection: " + release.toString());
		
		String objectId = null;
		try {
			objectId = mongoProvider.insert(release.toString(), "garbage");
		} catch (PersistenceException e) {
			// TODO define code errors
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(objectId, HttpStatus.OK);
	}
	
	@ApiMethod(description="Actualiza un disco existente en la base de datos", 
			   verb=ApiVerb.PUT,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="200", description="OK"),
			  			  @ApiError(code="500", description="Internal Server Error")})	
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public @ApiResponseObject ResponseEntity<Object> update(@ApiBodyObject @RequestBody Release release) {
		return null;
	}	
	
	@ApiMethod(description="Borra un disco existente en la base de datos", 
			   verb=ApiVerb.DELETE,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="200", description="OK"),
			  			  @ApiError(code="500", description="Internal Server Error")})	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public @ApiResponseObject ResponseEntity<Object> delete(@ApiPathParam(name="id", format="String", description="ID del objeto a borrar en la base de datos") 
	  														@PathVariable String id) {
		try {
			boolean result = mongoProvider.delete(id, "garbage");
			
			String message = "";
			if (result) {
				message = "Document sucessfully deleted";
			} else {
				message = "Document can't be deleted from database";
			}
			
			return new ResponseEntity<Object>(message, HttpStatus.OK);
			
		} catch (PersistenceException e) {
			// TODO define code errors
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}	

}

