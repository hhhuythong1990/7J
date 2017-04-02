package com.sevenj.web.model;

import com.sevenj.model.ToolPlan;

public class CreatedToolPlan extends ToolPlan {
	private String customerSpecificRatingCodeNew;
	private String capThresholdCodeNew;

	public String getCustomerSpecificRatingCodeNew() {
		return customerSpecificRatingCodeNew;
	}

	public void setCustomerSpecificRatingCodeNew(
			String customerSpecificRatingCodeNew) {
		this.customerSpecificRatingCodeNew = customerSpecificRatingCodeNew;
	}

	public String getCapThresholdCodeNew() {
		return capThresholdCodeNew;
	}

	public void setCapThresholdCodeNew(String capThresholdCodeNew) {
		this.capThresholdCodeNew = capThresholdCodeNew;
	}

}
