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
	
	/**
	 * Returns a JSON/String representation of this object
	 */
	public String toString() {
		StringBuffer documentSt = new StringBuffer("{");
		
		if (this.author != null && !"".equals(author)) {
			documentSt.append("\"author\": ")
					  .append("\"")
					  .append(this.author)
					  .append("\"")
					  .append(",");
		}
		
		if (this.title != null && !"".equals(title)) {
			documentSt.append("\"title\": ")
					  .append("\"")
					  .append(this.title)
					  .append("\"")
					  .append(",");
		}		
		
		if (this.releaseDate != null && !"".equals(releaseDate)) {
			documentSt.append("\"releaseDate\": ")
					  .append("\"")
					  .append(this.releaseDate)
					  .append("\"")
					  .append(",");
		}		
		
		if (this.catalogNumber != null && !"".equals(catalogNumber)) {
			documentSt.append("\"catalogNumber\": ")
					  .append("\"")
					  .append(this.catalogNumber)
					  .append("\"")
					  .append(",");
		}	
		
		if (this.format != null && !"".equals(format)) {
			documentSt.append("\"format\": ")
					  .append("\"")
					  .append(this.format)
					  .append("\"")
					  .append(",");
		}	
		
		if (this.country != null && !"".equals(country)) {
			documentSt.append("\"country\": ")
					  .append("\"")
					  .append(this.country)
					  .append("\"")
					  .append(",");
		}
		
		if (this.label != null && !"".equals(label)) {
			documentSt.append("\"label\": ")
					  .append("\"")
					  .append(this.label)
					  .append("\"")
					  .append(",");
		}		
		
		documentSt.deleteCharAt(documentSt.length()-1); // remove last comma
		documentSt.append("}");
		
		return documentSt.toString();
	}

}
