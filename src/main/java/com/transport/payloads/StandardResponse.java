package com.transport.payloads;

public class StandardResponse {

	private String message;

	public StandardResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "StandardResponse [message=" + message + "]";
	}	
}
