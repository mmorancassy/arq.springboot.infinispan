package es.arq.platform.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *{
 *		"instance_id": 67567847,
 *		"date_added": "2014-02-09T11:54:12-08:00",
 *		"basic_information": {
 *			"labels": [{
 *				"name": "[PIAS] Recordings",
 *				"entity_type": "1",
 *				"catno": "PIASR311CDX",
 *				"resource_url": "https://api.discogs.com/labels/61218",
 *				"id": 61218,
 *				"entity_type_name": "Label"
 *			}, {
 *				"name": "[PIAS] Recordings",
 *				"entity_type": "1",
 *				"catno": "945.U311.022",
 *				"resource_url": "https://api.discogs.com/labels/61218",
 *				"id": 61218,
 *				"entity_type_name": "Label"
 *			}],
 *			"formats": [{
 *				"descriptions": [
 *					"Album"
 *				],
 *				"text": "Digipak",
 *				"name": "CD",
 *				"qty": "1"
 *			}],
 *			"thumb": "",
 *			"title": "Anastasis",
 *			"artists": [{
 *				"join": ",",
 *				"name": "Dead Can Dance",
 *				"anv": "",
 *				"tracks": "",
 *				"role": "",
 *				"resource_url": "https://api.discogs.com/artists/12368",
 *				"id": 12368
 *			}],
 *			"resource_url": "https://api.discogs.com/releases/3791972",
 *			"year": 2012,
 *			"id": 3791972
 *		},
 *		"id": 3791972,
 *		"rating": 5
 *}
 * @author mmoran
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscogsItem {
	
	private String instance_id;
	
	private String date_added;
	
	private String id;
	
	private String rating;
	
	private DiscogsBasicInformation basic_information;

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public DiscogsBasicInformation getBasic_information() {
		return basic_information;
	}

	public void setBasic_information(DiscogsBasicInformation basic_information) {
		this.basic_information = basic_information;
	}

	@Override
	public String toString() {
		return "{\"instance_id\": \"" + instance_id + "\", \"date_added\": \""
				+ date_added + "\", \"id\": \"" + id + "\", \"rating\": \"" + rating
				+ "\", \"basic_information\": " + basic_information.toString() + "}";
	}
	
}
