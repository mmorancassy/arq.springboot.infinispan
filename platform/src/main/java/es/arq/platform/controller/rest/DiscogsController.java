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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.arq.platform.controller.dto.DiscogsRelease;
import es.arq.platform.controller.dto.Release;
import es.arq.platform.controller.dto.StatusError;
import es.arq.platform.services.DiscogsService;

/**
 * Response codes:
 * 
 * 200 – OK – Eyerything is working
 * 201 – OK – New resource has been created
 * 304 – Not Modified – The client can use cached data
 * 400 – Bad Request – The request was invalid or cannot be served.
 * 401 – Unauthorized – The request requires an user authentication
 * 403 – Forbidden – The server understood the request, but is refusing it or the access is not allowed.
 * 404 – Not found – There is no resource behind the URI.
 * 422 – Unprocessable Entity – Should be used if the server cannot process the entity
 * 500 – Internal Server Error
 * 
 * @author mmoran
 *
 */

@Api(name = "Discogs Releases service", 
	 description = "Methods for managing Discogs personal collection", 
	 visibility = ApiVisibility.PUBLIC, 
	 stage = ApiStage.ALPHA)
@RestController
@RequestMapping(value="/api/v1/discogs", produces=MediaType.APPLICATION_JSON_VALUE)
public class DiscogsController {

	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(DiscogsController.class);
	
	// Release collection
	private static final String RELEASE_COLLECTION = "releases";

	@Autowired
	private DiscogsService discogsService;
	
	@ApiMethod(description="Devuelve un documento con la información relativa a un disco", 
			   verb=ApiVerb.GET,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="400", description="Bad Request"),
						  @ApiError(code="401", description="Unauthorized"),
						  @ApiError(code="403", description="Forbidden"),
						  @ApiError(code="404", description="Not found"),
						  @ApiError(code="500", description="Internal Server Error")})
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE}, headers={HttpHeaders.AUTHORIZATION})		
    @ResponseBody
    public @ApiResponseObject ResponseEntity<Object> find(@ApiPathParam(name="id", format="String", description="ID del objeto a recuperar de la base de datos") 
    													  @PathVariable String id) {
    	String document = null;
    	try {
    		document = null;
    		
			return new ResponseEntity<Object>(document, HttpStatus.OK);
			
		} catch (Exception e) {
			StatusError errorDTO = new StatusError();
			errorDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorDTO.setErrorMessage(e.getMessage());
			return new ResponseEntity<Object>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
	@ApiMethod(description="Devuelve todos los documentos existentes en la colección de discos", 
			   verb=ApiVerb.GET,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="400", description="Bad Request"),
						  @ApiError(code="401", description="Unauthorized"),
						  @ApiError(code="403", description="Forbidden"),
						  @ApiError(code="404", description="Not found"),			
			  			  @ApiError(code="500", description="Internal Server Error")})	
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, headers={HttpHeaders.AUTHORIZATION})
    @ResponseBody
    public @ApiResponseObject ResponseEntity<DiscogsRelease> findAll() {
		DiscogsRelease document = null;
    	try {
    		// TODO add RequestParam for limit + query parameters
    		document = discogsService.findCollectionByUser("danzig6661");
			
    		return new ResponseEntity<DiscogsRelease>(document, HttpStatus.OK);
    		
		} catch (Exception e) {
			// TODO tratamiento de errores
			StatusError errorDTO = new StatusError();
			errorDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorDTO.setErrorMessage(e.getMessage());
			return new ResponseEntity<DiscogsRelease>(new DiscogsRelease(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@ApiMethod(description="Inserta un nuevo disco en la base de datos", 
			   verb=ApiVerb.POST,
			   responsestatuscode="201",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="400", description="Bad Request"),
						  @ApiError(code="401", description="Unauthorized"),
						  @ApiError(code="403", description="Forbidden"),
						  @ApiError(code="404", description="Not found"),			
			  			  @ApiError(code="500", description="Internal Server Error")})	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, headers={HttpHeaders.AUTHORIZATION})
	@ResponseBody	
	public @ApiResponseObject ResponseEntity<Object> insert(@ApiBodyObject @RequestBody Release release) {
		LOG.info("Document to insert in collection: " + release.toString());
		
		String objectId = null;
		try {
			objectId = null;
			
			return new ResponseEntity<Object>(objectId, HttpStatus.CREATED);
			
		} catch (Exception e) {
			StatusError errorDTO = new StatusError();
			errorDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorDTO.setErrorMessage(e.getMessage());
			return new ResponseEntity<Object>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiMethod(description="Actualiza un disco existente en la base de datos", 
			   verb=ApiVerb.PUT,
			   responsestatuscode="200",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="400", description="Bad Request"),
						  @ApiError(code="401", description="Unauthorized"),
						  @ApiError(code="403", description="Forbidden"),
						  @ApiError(code="404", description="Not found"),	
			  			  @ApiError(code="500", description="Internal Server Error")})	
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, headers={HttpHeaders.AUTHORIZATION})
	@ResponseBody	
	public @ApiResponseObject ResponseEntity<Object> update(@ApiBodyObject @RequestBody Release release) {
		String updatedDocument = null;
		try {
			updatedDocument = null;		
			
			return new ResponseEntity<Object>(updatedDocument, HttpStatus.OK);
			
		} catch (Exception e) {
			StatusError errorDTO = new StatusError();
			errorDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorDTO.setErrorMessage(e.getMessage());
			return new ResponseEntity<Object>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}	
	
	@ApiMethod(description="Borra un disco existente en la base de datos", 
			   verb=ApiVerb.DELETE,
			   responsestatuscode="204",
			   produces={MediaType.APPLICATION_JSON_VALUE})
	@ApiErrors(apierrors={@ApiError(code="400", description="Bad Request"),
						  @ApiError(code="401", description="Unauthorized"),
						  @ApiError(code="403", description="Forbidden"),
						  @ApiError(code="404", description="Not found"),	
			  			  @ApiError(code="500", description="Internal Server Error")})	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE, headers={HttpHeaders.AUTHORIZATION})
	@ResponseBody	
	public @ApiResponseObject ResponseEntity<Object> delete(@ApiPathParam(name="id", format="String", description="ID del objeto a borrar en la base de datos") 
	  														@PathVariable String id) {
		try {
			boolean result = false;
			
			String message = "";
			if (result) {
				message = "Document sucessfully deleted";
			} else {
				message = "Document can't be deleted from database";
			}
			
			return new ResponseEntity<Object>(message, HttpStatus.OK);
			
		} catch (Exception e) {
			StatusError errorDTO = new StatusError();
			errorDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorDTO.setErrorMessage(e.getMessage());
			return new ResponseEntity<Object>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

}

