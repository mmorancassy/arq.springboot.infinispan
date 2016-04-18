package es.arq.platform.controller.dto;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="Release", description="Información detallada de un disco")
public class Release {
	
	@ApiObjectField(description = "Group/Author name")
	private String author;
	
	@ApiObjectField(description = "Album title")
	private String title;
	
	@ApiObjectField(description = "Release date")
	private String releaseDate;
	
	@ApiObjectField(description = "Catalog number")
	private String catalogNumber;
	
	@ApiObjectField(description = "Release format -LP/CD/Cassette/DVD/VHS")
	private String format;
	
	@ApiObjectField(description = "Origin country")
	private String country;
	
	@ApiObjectField(description = "Label")
	private String label;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
