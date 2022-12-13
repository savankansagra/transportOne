package com.transport.payloads;

public class FileResponse {
	private String filename;
	private String url;
	private String type;
	private long size;
	
	public FileResponse() {
		super();
	}

	public FileResponse(String filename, String url, String type, long size) {
		super();
		this.filename = filename;
		this.url = url;
		this.type = type;
		this.size = size;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
}
