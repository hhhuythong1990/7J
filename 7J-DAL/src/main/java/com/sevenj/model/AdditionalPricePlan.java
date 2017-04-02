package com.sevenj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "additionalpriceplan")
public class AdditionalPricePlan {
	private String pricePlanCode;
	private String pricePlanName;
	private Date creationDate;
	private String creationUser;
	private int accountLevel;
	private int priorityCode;
	private String usoc;
	private int isToolPricingPlan;
	private int noOverseaDest;
	private int top28OverseaDest;
	private int t29OverseaDest;
	private int blockOfTime;
	private int dollarCap;
	private int quantity;
	private int costCompareOnRule;
	private int shortHaulRule;
	private int withSurcharges;
	private int multiRateSchedule;
	private int airTime;
	private int volumeDiscount;
	private int contractDiscount;
	private int increments60;
	private int increments60or30;
	private int increments30;
	private int increments10;
	private int increments1;
	private int peakOffPeak;
	private int is247;
	private int thd1;
	private int billMinium;
	private String description;
	private int lineLevel;
	private int provincialOntarioOnly;
	private int provincialQuebecOnly;
	private int	planType;

	@Id
	@Column(name = "PricePlanCode", nullable=false)
	@JsonProperty("PricePlanCode")
	@NotEmpty
	@Size(min=4, max=4)
	public String getPricePlanCode() {
		return pricePlanCode;
	}

	public void setPricePlanCode(String pricePlanCode) {
		this.pricePlanCode = pricePlanCode;
	}

	@Column(name = "PricePlanName", nullable=false)
	@JsonProperty("PricePlanName")
	@NotEmpty
	@Size(max=255)
	public String getPricePlanName() {
		return pricePlanName;
	}

	public void setPricePlanName(String pricePlanName) {
		this.pricePlanName = pricePlanName;
	}

	@Column(name = "CreationDate", nullable=false)
	@JsonProperty("CreationDate")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "CreationUser", nullable=false)
	@JsonProperty("CreationUser")
	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@Column(name = "AccountLevel", nullable = false)
	@JsonProperty("AccountLevel")
	@NotNull
	public int getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(int accountLevel) {
		this.accountLevel = accountLevel;
	}

	@Column(name = "PrioprityCode", nullable = false)
	@JsonProperty("PrioprityCode")
	@NotNull
	public int getPriorityCode() {
		return priorityCode;
	}

	public void setPriorityCode(int priorityCode) {
		this.priorityCode = priorityCode;
	}

	@Column(name = "Usoc", nullable = false)
	@JsonProperty("Usoc")
	public String getUsoc() {
		return usoc;
	}

	public void setUsoc(String usoc) {
		this.usoc = usoc;
	}

	@Column(name = "IsToolPricingPlan", nullable = false)
	@JsonProperty("IsToolPricingPlan")
	@NotNull
	public int getIsToolPricingPlan() {
		return isToolPricingPlan;
	}

	public void setIsToolPricingPlan(int isToolPricingPlan) {
		this.isToolPricingPlan = isToolPricingPlan;
	}

	@Column(name = "NoOverseaDest", nullable = false)
	@JsonProperty("NoOverseaDest")
	@NotNull
	public int getNoOverseaDest() {
		return noOverseaDest;
	}

	public void setNoOverseaDest(int noOverseaDest) {
		this.noOverseaDest = noOverseaDest;
	}

	@Column(name = "Top28OverseaDest", nullable = false)
	@JsonProperty("Top28OverseaDest")
	@NotNull
	public int getTop28OverseaDest() {
		return top28OverseaDest;
	}

	public void setTop28OverseaDest(int top28OverseaDest) {
		this.top28OverseaDest = top28OverseaDest;
	}

	@Column(name = "T29OverseaDest", nullable= false)
	@JsonProperty("T29OverseaDest")
	@NotNull
	public int getT29OverseaDest() {
		return t29OverseaDest;
	}

	public void setT29OverseaDest(int t29OverseaDest) {
		this.t29OverseaDest = t29OverseaDest;
	}

	@Column(name = "BlockOfTime", nullable=false)
	@JsonProperty("BlockOfTime")
	@NotNull
	public int getBlockOfTime() {
		return blockOfTime;
	}

	public void setBlockOfTime(int blockOfTime) {
		this.blockOfTime = blockOfTime;
	}

	@Column(name = "DollarCap", nullable=false)
	@JsonProperty("DollarCap")
	@NotNull
	public int getDollarCap() {
		return dollarCap;
	}

	public void setDollarCap(int dollarCap) {
		this.dollarCap = dollarCap;
	}

	@Column(name = "Quantity", nullable=false)
	@JsonProperty("Quantity")
	@NotNull
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "CostCompareOnRule", nullable=false)
	@JsonProperty("CostCompareOnRule")
	@NotNull
	public int getCostCompareOnRule() {
		return costCompareOnRule;
	}

	public void setCostCompareOnRule(int costCompareOnRule) {
		this.costCompareOnRule = costCompareOnRule;
	}

	@Column(name = "ShortHaulRule", nullable=false)
	@JsonProperty("ShortHaulRule")
	@NotNull
	public int getShortHaulRule() {
		return shortHaulRule;
	}

	public void setShortHaulRule(int shortHaulRule) {
		this.shortHaulRule = shortHaulRule;
	}

	@Column(name = "WithSurcharges", nullable=false)
	@JsonProperty("WithSurcharges")
	@NotNull
	public int getWithSurcharges() {
		return withSurcharges;
	}

	public void setWithSurcharges(int withSurcharges) {
		this.withSurcharges = withSurcharges;
	}

	@Column(name = "MultiRateSchedule", nullable=false)
	@JsonProperty("MultiRateSchedule")
	@NotNull
	public int getMultiRateSchedule() {
		return multiRateSchedule;
	}

	public void setMultiRateSchedule(int multiRateSchedule) {
		this.multiRateSchedule = multiRateSchedule;
	}

	@Column(name = "AirTime", nullable=false)
	@JsonProperty("AirTime")
	@NotNull
	public int getAirTime() {
		return airTime;
	}

	public void setAirTime(int airTime) {
		this.airTime = airTime;
	}

	@Column(name = "VolumeDiscount", nullable=false)
	@JsonProperty("VolumeDiscount")
	@NotNull
	public int getVolumeDiscount() {
		return volumeDiscount;
	}

	public void setVolumeDiscount(int volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	@Column(name = "ContractDiscount",nullable = false)
	@JsonProperty("ContractDiscount")
	@NotNull
	public int getContractDiscount() {
		return contractDiscount;
	}

	public void setContractDiscount(int contractDiscount) {
		this.contractDiscount = contractDiscount;
	}

	@Column(name = "Increments60", nullable=false)
	@JsonProperty("Increments60")
	@NotNull
	public int getIncrements60() {
		return increments60;
	}

	public void setIncrements60(int increments60) {
		this.increments60 = increments60;
	}

	@Column(name = "Increments60or30", nullable = false)
	@JsonProperty("Increments60or30")
	@NotNull
	public int getIncrements60or30() {
		return increments60or30;
	}

	public void setIncrements60or30(int increments60or30) {
		this.increments60or30 = increments60or30;
	}

	@Column(name = "Increments30", nullable = false)
	@JsonProperty("Increments30")
	@NotNull
	public int getIncrements30() {
		return increments30;
	}

	public void setIncrements30(int increments30) {
		this.increments30 = increments30;
	}

	@Column(name = "Increments10", nullable = false)
	@JsonProperty("Increments10")
	@NotNull
	public int getIncrements10() {
		return increments10;
	}

	public void setIncrements10(int increments10) {
		this.increments10 = increments10;
	}

	@Column(name = "Increments1", nullable = false)
	@JsonProperty("Increments1")
	@NotNull
	public int getIncrements1() {
		return increments1;
	}

	public void setIncrements1(int increments1) {
		this.increments1 = increments1;
	}

	@Column(name = "PeakOffPeak", nullable = false)
	@JsonProperty("PeakOffPeak")
	@NotNull
	public int getPeakOffPeak() {
		return peakOffPeak;
	}

	public void setPeakOffPeak(int peakOffPeak) {
		this.peakOffPeak = peakOffPeak;
	}

	@Column(name = "Is247", nullable = false)
	@JsonProperty("Is247")
	@NotNull
	public int getIs247() {
		return is247;
	}

	public void setIs247(int is247) {
		this.is247 = is247;
	}

	@Column(name = "Thd1", nullable = false)
	@JsonProperty("Thd1")
	@NotNull
	public int getThd1() {
		return thd1;
	}

	public void setThd1(int thd1) {
		this.thd1 = thd1;
	}

	@Column(name = "BillMinium", nullable = false)
	@JsonProperty("BillMinium")
	@NotNull
	public int getBillMinium() {
		return billMinium;
	}

	public void setBillMinium(int billMinium) {
		this.billMinium = billMinium;
	}
	
	@Column(name="Description")
	@JsonProperty("Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "LineLevel", nullable = false)
	@JsonProperty("LineLevel")
	@NotNull
	public int getLineLevel() {
		return lineLevel;
	}

	public void setLineLevel(int lineLevel) {
		this.lineLevel = lineLevel;
	}

	@Column(name = "ProvincialOntarioOnly", nullable = false)
	@JsonProperty("ProvincialOntarioOnly")
	@NotNull
	public int getProvincialOntarioOnly() {
		return provincialOntarioOnly;
	}

	public void setProvincialOntarioOnly(int provincialOntarioOnly) {
		this.provincialOntarioOnly = provincialOntarioOnly;
	}

	@Column(name = "ProvincialQuebecOnly", nullable = false)
	@JsonProperty("ProvincialQuebecOnly")
	@NotNull
	public int getProvincialQuebecOnly() {
		return provincialQuebecOnly;
	}

	public void setProvincialQuebecOnly(int provincialQuebecOnly) {
		this.provincialQuebecOnly = provincialQuebecOnly;
	}

	@Column(name = "PlanType", nullable = false)
	@JsonProperty("PlanType")
	@NotNull
	public int getPlanType() {
		return planType;
	}

	public void setPlanType(int planType) {
		this.planType = planType;
	}

}
