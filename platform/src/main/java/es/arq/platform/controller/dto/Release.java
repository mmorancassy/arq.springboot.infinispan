package es.arq.platform.controller.dto;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Example Release json document:
 * 
 * {
 *  "_id": {
 *    "$oid": "571a14d5a54204e993f4c559"
 *  },
 *  "catalog#": "74321 68596 2-GUN 185",
 *  "artist": "Rage (6)",
 *  "title": "Ghosts",
 *  "label": "BMG-GUN Records (2)",
 *  "format": "CD-Album",
 *  "rating": "5",
 *  "released": "1999",
 *  "release_id": "554694",
 *  "collectionfolder": "Power Metal",
 *  "date added": "2011-08-21 06:53:37",
 *  "collection style": "Power Metal"
 *}
 * @author mmoran
 *
 */

@ApiObject(name="Release", description="Music release detail information")
public class Release {
	
	@ApiObjectField(description = "object Id")
	private String _id;
	
	@ApiObjectField(description = "Group/Author name")
	private String artist;
	
	@ApiObjectField(description = "Album title")
	private String title;
	
	@ApiObjectField(description = "Release year")
	private String year;
	
	@ApiObjectField(description = "Catalog number")
	private String catalogNumber;
	
	@ApiObjectField(description = "Release format -LP/CD/Cassette/DVD/VHS")
	private String format;
	
	@ApiObjectField(description = "Origin country")
	private String country;
	
	@ApiObjectField(description = "Label")
	private String label;
	
	@ApiObjectField(description = "Album rate 1 to 5 stars")
	private String rating;
	
	@ApiObjectField(description = "Collection folder")
	private String collectionfolder;
	
	@ApiObjectField(description = "Style")
	private String style;
	
	@ApiObjectField(description = "Date added - format yyyy-MM-dd hh:mm:ss")
	private String dateAdded;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getCollectionfolder() {
		return collectionfolder;
	}

	public void setCollectionfolder(String collectionfolder) {
		this.collectionfolder = collectionfolder;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * Returns a JSON/String representation of this object
	 */
	public String toString() {
		StringBuffer documentSt = new StringBuffer("{");
		
		if (this._id != null && !"".equals(this._id)) {
			documentSt.append("\"_id\": { $oid: ")
					  .append("\"")
					  .append(this._id)
					  .append("\" }, ");	
		}
		
		if (this.artist != null && !"".equals(this.artist)) {
			documentSt.append("\"artist\": ")
					  .append("\"")
					  .append(this.artist)
					  .append("\"")
					  .append(",");
		}
		
		if (this.title != null && !"".equals(this.title)) {
			documentSt.append("\"title\": ")
					  .append("\"")
					  .append(this.title)
					  .append("\"")
					  .append(",");
		}		
		
		if (this.year != null && !"".equals(this.year)) {
			documentSt.append("\"released\": ")
					  .append("\"")
					  .append(this.year)
					  .append("\"")
					  .append(",");
		}		
		
		if (this.catalogNumber != null && !"".equals(this.catalogNumber)) {
			documentSt.append("\"catalog\": ")
					  .append("\"")
					  .append(this.catalogNumber)
					  .append("\"")
					  .append(",");
		}	
		
		if (this.format != null && !"".equals(this.format)) {
			documentSt.append("\"format\": ")
					  .append("\"")
					  .append(this.format)
					  .append("\"")
					  .append(",");
		}	
		
		if (this.country != null && !"".equals(this.country)) {
			documentSt.append("\"country\": ")
					  .append("\"")
					  .append(this.country)
					  .append("\"")
					  .append(",");
		}
		
		if (this.label != null && !"".equals(this.label)) {
			documentSt.append("\"label\": ")
					  .append("\"")
					  .append(this.label)
					  .append("\"")
					  .append(",");
		}		
		
		if (this.collectionfolder != null && !"".equals(this.collectionfolder)) {
			documentSt.append("\"collectionfolder\": ")
					  .append("\"")
					  .append(this.collectionfolder)
					  .append("\"")
					  .append(",");
		}
		
		if (this.rating != null && !"".equals(this.rating)) {
			documentSt.append("\"rating\": ")
					  .append("\"")
					  .append(this.rating)
					  .append("\"")
					  .append(",");
		}		
		
		if (this.style != null && !"".equals(this.style)) {
			documentSt.append("\"collection style\": ")
					  .append("\"")
					  .append(this.style)
					  .append("\"")
					  .append(",");
		}		
		
		if (this.dateAdded != null && !"".equals(this.dateAdded)) {
			documentSt.append("\"date added\": ")
					  .append("\"")
					  .append(this.dateAdded)
					  .append("\"")
					  .append(",");
		}			
		
		documentSt.deleteCharAt(documentSt.length()-1); // remove last comma
		documentSt.append("}");
		
		return documentSt.toString();
	}

}
