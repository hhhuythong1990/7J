package com.sevenj.web.model;

import com.sevenj.model.AdditionalPricePlan;

public class AdditionalPricePlanView extends AdditionalPricePlan {
	private String priorityCodeNew;
	private String blockOfTimeNew;
	
	public AdditionalPricePlanView(){
		setUsoc("");
	}

	public String getPriorityCodeNew() {
		return priorityCodeNew;
	}

	public void setPriorityCodeNew(String priorityCodeNew) {
		this.priorityCodeNew = priorityCodeNew;
	}

	public String getBlockOfTimeNew() {
		return blockOfTimeNew;
	}

	public void setBlockOfTimeNew(String blockOfTimeNew) {
		this.blockOfTimeNew = blockOfTimeNew;
	}

}
