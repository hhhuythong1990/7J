package com.sevenj.web.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;


public class ToolPlanModel {
	private int ID;
	private String pricePlan;
	private String weekDefinitionCode;
	private String ratePeriodSequenceNumber;
	private String typeOfCall;
	private String speacialTypeOfCall;
	private String originatingDestinationCode;
	private String originatingSatelliteCode;
	private String terminatingDestinationCode;
	private String terminatingSatelliteCode;
	private String rateCapSequenceNumber;
	private String customerSpecificRatingCode;
	private Date tableEntryEffectiveDate;
	private String rateType;
	private String rate;
	private String rateTimeUnit;
	private String messageLevelPercentDiscount;
	private String capThresholdCode;
	private String minimumTime;
	private String incrementTime;
	private String incrementTimeUnit;
	private String rateValidationIndicator;
	private Date tableEntryExpirationDate;
	private Date tableEntryCreationDate;
	private String tableEntryCreationUser;
	private String status;
	private String checkOutUser;
	private String approveStatus;
	private String cbssStatus;

	@JsonProperty("ID")
	public int getID() {
		return ID;
	}

	public void setID(int i) {
		ID = i;
	}

	@JsonProperty("PricePlan")
	public String getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(String pricePlan) {
		this.pricePlan = pricePlan;
	}

	@JsonProperty("weekDefinitionCode")
	public String getWeekDefinitionCode() {
		return weekDefinitionCode;
	}

	public void setWeekDefinitionCode(String weekDefinitionCode) {
		this.weekDefinitionCode = weekDefinitionCode;
	}

	@JsonProperty("ratePeriodSequenceNumber")
	public String getRatePeriodSequenceNumber() {
		return ratePeriodSequenceNumber;
	}

	public void setRatePeriodSequenceNumber(String ratePeriodSequenceNumber) {
		this.ratePeriodSequenceNumber = ratePeriodSequenceNumber;
	}

	@JsonProperty("typeOfCall")
	public String getTypeOfCall() {
		return typeOfCall;
	}

	public void setTypeOfCall(String typeOfCall) {
		this.typeOfCall = typeOfCall;
	}

	@JsonProperty("speacialTypeOfCall")
	public String getSpeacialTypeOfCall() {
		return speacialTypeOfCall;
	}

	public void setSpeacialTypeOfCall(String speacialTypeOfCall) {
		this.speacialTypeOfCall = speacialTypeOfCall;
	}

	@JsonProperty("originatingDestinationCode")
	public String getOriginatingDestinationCode() {
		return originatingDestinationCode;
	}

	public void setOriginatingDestinationCode(String originatingDestinationCode) {
		this.originatingDestinationCode = originatingDestinationCode;
	}

	@JsonProperty("originatingSatelliteCode")
	public String getOriginatingSatelliteCode() {
		return originatingSatelliteCode;
	}

	public void setOriginatingSatelliteCode(String originatingSatelliteCode) {
		this.originatingSatelliteCode = originatingSatelliteCode;
	}

	@JsonProperty("terminatingDestinationCode")
	public String getTerminatingDestinationCode() {
		return terminatingDestinationCode;
	}

	public void setTerminatingDestinationCode(String terminatingDestinationCode) {
		this.terminatingDestinationCode = terminatingDestinationCode;
	}

	@JsonProperty("terminatingSatelliteCode")
	public String getTerminatingSatelliteCode() {
		return terminatingSatelliteCode;
	}

	public void setTerminatingSatelliteCode(String terminatingSatelliteCode) {
		this.terminatingSatelliteCode = terminatingSatelliteCode;
	}

	@JsonProperty("rateCapSequenceNumber")
	public String getRateCapSequenceNumber() {
		return rateCapSequenceNumber;
	}

	public void setRateCapSequenceNumber(String rateCapSequenceNumber) {
		this.rateCapSequenceNumber = rateCapSequenceNumber;
	}

	@JsonProperty("customerSpecificRatingCode")
	public String getCustomerSpecificRatingCode() {
		return customerSpecificRatingCode;
	}

	public void setCustomerSpecificRatingCode(String customerSpecificRatingCode) {
		this.customerSpecificRatingCode = customerSpecificRatingCode;
	}

	@JsonProperty("tableEntryEffectiveDate")
	public Date getTableEntryEffectiveDate() {
		return tableEntryEffectiveDate;
	}

	public void setTableEntryEffectiveDate(Date tableEntryEffectiveDate) {
		this.tableEntryEffectiveDate = tableEntryEffectiveDate;
	}

	@JsonProperty("rateType")
	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	@JsonProperty("rate")
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@JsonProperty("rateTimeUnit")
	public String getRateTimeUnit() {
		return rateTimeUnit;
	}

	public void setRateTimeUnit(String rateTimeUnit) {
		this.rateTimeUnit = rateTimeUnit;
	}

	@JsonProperty("messageLevelPercentDiscount")
	public String getMessageLevelPercentDiscount() {
		return messageLevelPercentDiscount;
	}

	public void setMessageLevelPercentDiscount(
			String messageLevelPercentDiscount) {
		this.messageLevelPercentDiscount = messageLevelPercentDiscount;
	}

	@JsonProperty("capThresholdCode")
	public String getCapThresholdCode() {
		return capThresholdCode;
	}

	public void setCapThresholdCode(String capThresholdCode) {
		this.capThresholdCode = capThresholdCode;
	}

	@JsonProperty("minimumTime")
	public String getMinimumTime() {
		return minimumTime;
	}

	public void setMinimumTime(String minimumTime) {
		this.minimumTime = minimumTime;
	}

	@JsonProperty("incrementTime")
	public String getIncrementTime() {
		return incrementTime;
	}

	public void setIncrementTime(String incrementTime) {
		this.incrementTime = incrementTime;
	}

	@JsonProperty("incrementTimeUnit")
	public String getIncrementTimeUnit() {
		return incrementTimeUnit;
	}

	public void setIncrementTimeUnit(String incrementTimeUnit) {
		this.incrementTimeUnit = incrementTimeUnit;
	}

	@JsonProperty("rateValidationIndicator")
	public String getRateValidationIndicator() {
		return rateValidationIndicator;
	}

	public void setRateValidationIndicator(String rateValidationIndicator) {
		this.rateValidationIndicator = rateValidationIndicator;
	}

	@JsonProperty("tableEntryExpirationDate")
	public Date getTableEntryExpirationDate() {
		return tableEntryExpirationDate;
	}

	public void setTableEntryExpirationDate(Date tableEntryExpirationDate) {
		this.tableEntryExpirationDate = tableEntryExpirationDate;
	}

	@JsonProperty("tableEntryCreationDate")
	public Date getTableEntryCreationDate() {
		return tableEntryCreationDate;
	}

	public void setTableEntryCreationDate(Date tableEntryCreationDate) {
		this.tableEntryCreationDate = tableEntryCreationDate;
	}

	@JsonProperty("tableEntryCreationUser")
	public String getTableEntryCreationUser() {
		return tableEntryCreationUser;
	}

	public void setTableEntryCreationUser(String tableEntryCreationUser) {
		this.tableEntryCreationUser = tableEntryCreationUser;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("checkOutUser")
	public String getCheckOutUser() {
		return checkOutUser;
	}

	public void setCheckOutUser(String checkOutUser) {
		this.checkOutUser = checkOutUser;
	}

	@JsonProperty("approveStatus")
	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	@JsonProperty("cbssStatus")
	public String getCbssStatus() {
		return cbssStatus;
	}

	public void setCbssStatus(String cbssStatus) {
		this.cbssStatus = cbssStatus;
	}
	// @JsonProperty("comments")
	// public Set<Comment> getComments() {
	// return comments;
	// }
	// public void setComments(Set<Comment> comments) {
	// this.comments = comments;
	// }
	// @JsonProperty("histories")
	// public Set<History> getHistories() {
	// return histories;
	// }
	// public void setHistories(Set<History> histories) {
	// this.histories = histories;
	// }

}
