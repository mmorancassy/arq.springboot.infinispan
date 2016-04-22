package es.arq.platform.controller.dto;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="StatusError", description="Response error detail information")
public class StatusError {

	@ApiObjectField(description = "Rest status code")
	private int statusCode;
	
	@ApiObjectField(description = "Detail error message")
	private String errorMessage;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
