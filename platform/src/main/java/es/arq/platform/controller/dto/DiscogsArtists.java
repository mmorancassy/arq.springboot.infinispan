package es.arq.platform.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * "artists": [{
 *		"join": ",",
 *		"name": "Dead Can Dance",
 *		"anv": "",
 *		"tracks": "",
 *		"role": "",
 *		"resource_url": "https://api.discogs.com/artists/12368",
 *		"id": 12368
 *	}],
 * @author mmoran
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscogsArtists {

	private String join;
	
	private String name;
	
	private String anv;
	
	private String tracks;
	
	private String role;
	
	private String resource_url;
	
	private String id;

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnv() {
		return anv;
	}

	public void setAnv(String anv) {
		this.anv = anv;
	}

	public String getTracks() {
		return tracks;
	}

	public void setTracks(String tracks) {
		this.tracks = tracks;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	@Override
	public String toString() {
		return "{\"join\": \"" + join + "\", \"name\": \"" + name + "\", \"anv\": \""
				+ anv + "\", \"tracks\": \"" + tracks + "\", \"role\": \"" + role
				+ "\", \"resource_url\": \"" + resource_url + "\", \"id\": \"" + id + "\"}";
	}
	
}
