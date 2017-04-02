package com.sevenj.web.model;

import java.io.Serializable;
import java.util.Date;

public class UpdateToolPlanInfo implements Serializable{
	private String selectedToolPlanIds; 
	private Date tableEntryEffectiveDate;
	private double rate;
	private Date tableEntryExpirationDate;
	
	public UpdateToolPlanInfo(){
		rate = -1;
	}

	public Date getTableEntryEffectiveDate() {
		return tableEntryEffectiveDate;
	}

	public void setTableEntryEffectiveDate(Date tableEntryEffectiveDate) {
		this.tableEntryEffectiveDate = tableEntryEffectiveDate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Date getTableEntryExpirationDate() {
		return tableEntryExpirationDate;
	}

	public void setTableEntryExpirationDate(Date tableEntryExpirationDate) {
		this.tableEntryExpirationDate = tableEntryExpirationDate;
	}

	public String getSelectedToolPlanIds() {
		return selectedToolPlanIds;
	}

	public void setSelectedToolPlanIds(String selectedToolPlanIds) {
		this.selectedToolPlanIds = selectedToolPlanIds;
	}
}
