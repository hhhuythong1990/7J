package com.sevenj.model;

public enum PricePlanStatus {
	NEW(0, "New"), WAIT_FOR_APPROVE(1, "Wait for approve"), APPROVED(2,"Approved");
	private int statusCode;
	private String statusName;

	private PricePlanStatus(int statusCode, String statusName) {
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
