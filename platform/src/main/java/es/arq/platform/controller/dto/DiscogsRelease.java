package es.arq.platform.controller.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * {
 * 	"releases": [{
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
 *	}]
 *}
 * @author mmoran
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscogsRelease {
	
	private DiscogsItem[] releases;

	public DiscogsItem[] getReleases() {
		return releases;
	}

	public void setReleases(DiscogsItem[] releases) {
		this.releases = releases;
	}

	@Override
	public String toString() {
		return "{\"releases\": " + Arrays.toString(releases) + "}";
	}

}
