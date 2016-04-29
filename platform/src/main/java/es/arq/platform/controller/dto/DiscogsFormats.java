package es.arq.platform.controller.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * "formats": [{
 *    "descriptions": [
 *		  	 "Album"
 *	  ],
 *    "text": "Digipak",
 *	  "name": "CD",
 * 	  "qty": "1"
 *	}]
 * @author mmoran
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscogsFormats {

	private String text;
	
	private String name;
	
	private String qty;
	
	private String[] descriptions;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String[] getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String[] descriptions) {
		this.descriptions = descriptions;
	}
	
	private String charify(String value) {
		return "\"" + value + "\"";
	}

	@Override
	public String toString() {
		List<String> descriptionsList = Arrays.asList(descriptions);
		descriptionsList.forEach(item -> charify(item));
		return "{\"text\": \"" + text + "\", \"name\": \"" + name + "\", \"qty\": \""
				+ qty + "\", \"descriptions\": \"" + descriptionsList.toString() + "\"}";
	}

}
