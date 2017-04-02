package com.sevenj.model;

public enum CBSSStatus {
	LOCAL(0, "Price Plan Local"), INCBSS(1, "IN CBSS"),CHANGED(2,"CBSS Changed"), JUST_PASTE(7,"Just Paste");
	private int statusCode;
	private String statusName;

	private CBSSStatus(int statusCode, String statusName) {
		this.statusCode = statusCode;
		this.statusName = statusName;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
}
