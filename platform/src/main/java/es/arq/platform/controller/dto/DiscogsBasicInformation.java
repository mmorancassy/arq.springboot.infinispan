package es.arq.platform.controller.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *{
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
 *}
 * @author mmoran
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscogsBasicInformation {

	private String thumb;
	
	private String title;
	
	private String resource_url;
	
	private String year;
	
	private String id;
	
	private DiscogsLabels[] labels;
	
	private DiscogsFormats[] formats;
	
	private DiscogsArtists[] artists;

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResource_url() {
		return resource_url;
	}

	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public DiscogsLabels[] getLabels() {
		return labels;
	}

	public void setLabels(DiscogsLabels[] labels) {
		this.labels = labels;
	}
	
	public DiscogsFormats[] getFormats() {
		return formats;
	}

	public void setFormats(DiscogsFormats[] formats) {
		this.formats = formats;
	}

	public DiscogsArtists[] getArtists() {
		return artists;
	}

	public void setArtists(DiscogsArtists[] artists) {
		this.artists = artists;
	}

	@Override
	public String toString() {
		return "{\"thumb\": \"" + thumb + "\", \"title\": \"" + title
				+ "\", \"resource_url\": \"" + resource_url + "\", \"year\": \"" + year + "\", \"id\": \""
				+ id + "\", \"labels\": " + Arrays.toString(labels) + ", \"formats\": "
				+ Arrays.toString(formats) + ", \"artists\": "
				+ Arrays.toString(artists) + "}";
	}

}
