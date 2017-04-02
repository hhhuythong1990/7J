package com.sevenj.model;

public enum ToolPlanStatus {
	PENDING(0,"Pending"),APPROVED(1,"Approved"), REJECTED(2,"Rejected"), CHECKOUT(1,"Check Out"), CHECKIN(0,"Check In");
	private int statusCode;
	private String statusName;

	private ToolPlanStatus(int statusCode, String statusName) {
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
	
	public static ToolPlanStatus fromType(int type){
		ToolPlanStatus result = null;
		for(ToolPlanStatus status: ToolPlanStatus.values()){
			if (status.getStatusCode() == type){
				result = status;
				break;
			}
		}
		return result;
	}
}
