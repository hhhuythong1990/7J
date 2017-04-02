package com.sevenj.web.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class AdditionalPricePlanViewModel {
	private String pricePlanCode;
	private String pricePlanName;
	private Date creationDate;
	private String creationUser;
	private KeyValue<Integer, String> accountLevel;
	private KeyValue<Integer, String> prioprityCode;
	private String usoc;
	private KeyValue<Integer, String> isToolPricingPlan;
	private KeyValue<Integer, String> noOverseaDest;
	private KeyValue<Integer, String> top28OverseaDest;
	private KeyValue<Integer, String> t29OverseaDest;
	private KeyValue<Integer, String> blockOfTime;
	private KeyValue<Integer, String> dollarCap;
	private KeyValue<Integer, String> quantity;
	private KeyValue<Integer, String> costCompareOnRule;
	private KeyValue<Integer, String> shortHaulRule;
	private KeyValue<Integer, String> withSurcharges;
	private KeyValue<Integer, String> multiRateSchedule;
	private KeyValue<Integer, String> airTime;
	private KeyValue<Integer, String> volumeDiscount;
	private KeyValue<Integer, String> contractDiscount;
	private KeyValue<Integer, String> increments60;
	private KeyValue<Integer, String> increments60or30;
	private KeyValue<Integer, String> increments30;
	private KeyValue<Integer, String> increments10;
	private KeyValue<Integer, String> increments1;
	private KeyValue<Integer, String> peakOffPeak;
	private KeyValue<Integer, String> is247;
	private KeyValue<Integer, String> thd1;
	private KeyValue<Integer, String> billMinium;
	private String description;
	private KeyValue<Integer, String> lineLevel;
	private KeyValue<Integer, String> provincialOntarioOnly;
	private KeyValue<Integer, String> provincialQuebecOnly;
	private KeyValue<Integer, String> planType;

	@JsonProperty("PricePlanCode")
	public String getPricePlanCode() {
		return pricePlanCode;
	}

	public void setPricePlanCode(String pricePlanCode) {
		this.pricePlanCode = pricePlanCode;
	}

	@JsonProperty("PricePlanName")
	public String getPricePlanName() {
		return pricePlanName;
	}

	public void setPricePlanName(String pricePlanName) {
		this.pricePlanName = pricePlanName;
	}

	@JsonProperty("CreationDate")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@JsonProperty("CreationUser")
	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@JsonProperty("AccountLevel")
	public KeyValue<Integer, String> getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(KeyValue<Integer, String> accountLevel) {
		this.accountLevel = accountLevel;
	}

	@JsonProperty("PrioprityCode")
	public KeyValue<Integer, String> getPrioprityCode() {
		return prioprityCode;
	}

	public void setPrioprityCode(KeyValue<Integer, String> prioprityCode) {
		this.prioprityCode = prioprityCode;
	}

	@JsonProperty("Usoc")
	public String getUsoc() {
		return usoc;
	}

	public void setUsoc(String usoc) {
		this.usoc = usoc;
	}

	@JsonProperty("IsToolPricingPlan")
	public KeyValue<Integer, String> getIsToolPricingPlan() {
		return isToolPricingPlan;
	}

	public void setIsToolPricingPlan(KeyValue<Integer, String> isToolPricingPlan) {
		this.isToolPricingPlan = isToolPricingPlan;
	}

	@JsonProperty("NoOverseaDest")
	public KeyValue<Integer, String> getNoOverseaDest() {
		return noOverseaDest;
	}

	public void setNoOverseaDest(KeyValue<Integer, String> noOverseaDest) {
		this.noOverseaDest = noOverseaDest;
	}

	@JsonProperty("Top28OverseaDest")
	public KeyValue<Integer, String> getTop28OverseaDest() {
		return top28OverseaDest;
	}

	public void setTop28OverseaDest(KeyValue<Integer, String> top28OverseaDest) {
		this.top28OverseaDest = top28OverseaDest;
	}

	@JsonProperty("T29OverseaDest")
	public KeyValue<Integer, String> getT29OverseaDest() {
		return t29OverseaDest;
	}

	public void setT29OverseaDest(KeyValue<Integer, String> t29OverseaDest) {
		this.t29OverseaDest = t29OverseaDest;
	}

	@JsonProperty("BlockOfTime")
	public KeyValue<Integer, String> getBlockOfTime() {
		return blockOfTime;
	}

	public void setBlockOfTime(KeyValue<Integer, String> blockOfTime) {
		this.blockOfTime = blockOfTime;
	}

	@JsonProperty("DollarCap")
	public KeyValue<Integer, String> getDollarCap() {
		return dollarCap;
	}

	public void setDollarCap(KeyValue<Integer, String> dollarCap) {
		this.dollarCap = dollarCap;
	}

	@JsonProperty("Quantity")
	public KeyValue<Integer, String> getQuantity() {
		return quantity;
	}

	public void setQuantity(KeyValue<Integer, String> quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("CostCompareOnRule")
	public KeyValue<Integer, String> getCostCompareOnRule() {
		return costCompareOnRule;
	}

	public void setCostCompareOnRule(KeyValue<Integer, String> costCompareOnRule) {
		this.costCompareOnRule = costCompareOnRule;
	}

	@JsonProperty("ShortHaulRule")
	public KeyValue<Integer, String> getShortHaulRule() {
		return shortHaulRule;
	}

	public void setShortHaulRule(KeyValue<Integer, String> shortHaulRule) {
		this.shortHaulRule = shortHaulRule;
	}

	@JsonProperty("WithSurcharges")
	public KeyValue<Integer, String> getWithSurcharges() {
		return withSurcharges;
	}

	public void setWithSurcharges(KeyValue<Integer, String> withSurcharges) {
		this.withSurcharges = withSurcharges;
	}

	@JsonProperty("MultiRateSchedule")
	public KeyValue<Integer, String> getMultiRateSchedule() {
		return multiRateSchedule;
	}

	public void setMultiRateSchedule(KeyValue<Integer, String> multiRateSchedule) {
		this.multiRateSchedule = multiRateSchedule;
	}

	@JsonProperty("AirTime")
	public KeyValue<Integer, String> getAirTime() {
		return airTime;
	}

	public void setAirTime(KeyValue<Integer, String> airTime) {
		this.airTime = airTime;
	}

	@JsonProperty("VolumeDiscount")
	public KeyValue<Integer, String> getVolumeDiscount() {
		return volumeDiscount;
	}

	public void setVolumeDiscount(KeyValue<Integer, String> volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	@JsonProperty("ContractDiscount")
	public KeyValue<Integer, String> getContractDiscount() {
		return contractDiscount;
	}

	public void setContractDiscount(KeyValue<Integer, String> contractDiscount) {
		this.contractDiscount = contractDiscount;
	}

	@JsonProperty("Increments60")
	public KeyValue<Integer, String> getIncrements60() {
		return increments60;
	}

	public void setIncrements60(KeyValue<Integer, String> increments60) {
		this.increments60 = increments60;
	}

	@JsonProperty("Increments60or30")
	public KeyValue<Integer, String> getIncrements60or30() {
		return increments60or30;
	}

	public void setIncrements60or30(KeyValue<Integer, String> increments60or30) {
		this.increments60or30 = increments60or30;
	}

	@JsonProperty("Increments30")
	public KeyValue<Integer, String> getIncrements30() {
		return increments30;
	}

	public void setIncrements30(KeyValue<Integer, String> increments30) {
		this.increments30 = increments30;
	}

	@JsonProperty("Increments10")
	public KeyValue<Integer, String> getIncrements10() {
		return increments10;
	}

	public void setIncrements10(KeyValue<Integer, String> increments10) {
		this.increments10 = increments10;
	}

	@JsonProperty("Increments1")
	public KeyValue<Integer, String> getIncrements1() {
		return increments1;
	}

	public void setIncrements1(KeyValue<Integer, String> increments1) {
		this.increments1 = increments1;
	}

	@JsonProperty("PeakOffPeak")
	public KeyValue<Integer, String> getPeakOffPeak() {
		return peakOffPeak;
	}

	public void setPeakOffPeak(KeyValue<Integer, String> peakOffPeak) {
		this.peakOffPeak = peakOffPeak;
	}

	@JsonProperty("Is247")
	public KeyValue<Integer, String> getIs247() {
		return is247;
	}

	public void setIs247(KeyValue<Integer, String> is247) {
		this.is247 = is247;
	}

	@JsonProperty("Thd1")
	public KeyValue<Integer, String> getThd1() {
		return thd1;
	}

	public void setThd1(KeyValue<Integer, String> thd1) {
		this.thd1 = thd1;
	}

	@JsonProperty("BillMinium")
	public KeyValue<Integer, String> getBillMinium() {
		return billMinium;
	}

	public void setBillMinium(KeyValue<Integer, String> billMinium) {
		this.billMinium = billMinium;
	}

	public String getDescription() {
		return description;
	}

	@JsonProperty("Description")
	public void setDescription(String description) {
		this.description = description;
	}

	public KeyValue<Integer, String> getLineLevel() {
		return lineLevel;
	}

	@JsonProperty("LineLevel")
	public void setLineLevel(KeyValue<Integer, String> lineLevel) {
		this.lineLevel = lineLevel;
	}

	@JsonProperty("ProvincialOntarioOnly")
	public KeyValue<Integer, String> getProvincialOntarioOnly() {
		return provincialOntarioOnly;
	}

	public void setProvincialOntarioOnly(
			KeyValue<Integer, String> provincialOntarioOnly) {
		this.provincialOntarioOnly = provincialOntarioOnly;
	}

	@JsonProperty("ProvincialQuebecOnly")
	public KeyValue<Integer, String> getProvincialQuebecOnly() {
		return provincialQuebecOnly;
	}

	public void setProvincialQuebecOnly(
			KeyValue<Integer, String> provincialQuebecOnly) {
		this.provincialQuebecOnly = provincialQuebecOnly;
	}

	@JsonProperty("PlanType")
	public KeyValue<Integer, String> getPlanType() {
		return planType;
	}

	public void setPlanType(KeyValue<Integer, String> planType) {
		this.planType = planType;
	}

}