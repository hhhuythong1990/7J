package com.sevenj.web.model;

import com.sevenj.model.PricePlan;

public class PricePlanInfo extends PricePlan{
	private String pricePlanCode;
	private String pricePlanName;
	private int status;

	public String getPricePlanCode() {
		return pricePlanCode;
	}

	public void setPricePlanCode(String pricePlanCode) {
		this.pricePlanCode = pricePlanCode;
	}

	public String getPricePlanName() {
		return pricePlanName;
	}

	public void setPricePlanName(String pricePlanName) {
		this.pricePlanName = pricePlanName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
