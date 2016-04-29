package es.arq.platform.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 " labels": [{
 *	"name": "[PIAS] Recordings",
 *	"entity_type": "1",
 *	"catno": "PIASR311CDX",
 *	"resource_url": "https://api.discogs.com/labels/61218",
 *	"id": 61218,
 *	"entity_type_name": "Label"
 *	}]
 * @author mmoran
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscogsLabels {

	private String name;
	
	private String entity_type;
	
	private String catno;
	
	private String resource_url;
	
	private String id;
	
	private String entity_type_name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getCatno() {
		return catno;
	}

	public void setCatno(String catno) {
		this.catno = catno;
	}

	public String getResource_url() {
		return resource_url;
	}

	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity_type_name() {
		return entity_type_name;
	}

	public void setEntity_type_name(String entity_type_name) {
		this.entity_type_name = entity_type_name;
	}

	@Override
	public String toString() {
		return "{\"name\": \"" + name + "\", \"entity_type\": \"" + entity_type
				+ "\", \"catno\": \"" + catno + "\", \"resource_url\": \"" + resource_url
				+ "\", \"id\": \"" + id + "\", \"entity_type_name\": \"" + entity_type_name + "\"}";
	}
		
}
