package com.sevenj.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="toolplan")
public class ToolPlan {
	private int ID;
	private PricePlan pricePlan;
	private String weekDefinitionCode;
	private int ratePeriodSequenceNumber;
	private String typeOfCall;
	private String speacialTypeOfCall;
	private String originatingDestinationCode;
	private String originatingSatelliteCode;
	private String terminatingDestinationCode;
	private String terminatingSatelliteCode;
	private int rateCapSequenceNumber;
	private String customerSpecificRatingCode;
	private Date tableEntryEffectiveDate;
	private String rateType;
	private double rate;
	private String rateTimeUnit;
	private double messageLevelPercentDiscount;
	private String capThresholdCode;
	private int minimumTime;
	private int incrementTime;
	private String incrementTimeUnit;
	private String rateValidationIndicator;
	private Date tableEntryExpirationDate;
	private Date tableEntryCreationDate;
	private String tableEntryCreationUser;
	private int status;
	private String checkOutUser;
	private int approveStatus;
	private int cbssStatus;
	private Set<Comment> comments;
	private Set<History> histories;

	@Id
	@Column(name="ID", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("ID")
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@ManyToOne
	@JoinColumn(name="PricingPlanCode",nullable=false)
	@JsonProperty("PricePlan")
	public PricePlan getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(PricePlan pricePlan) {
		this.pricePlan = pricePlan;
	}

	@Column(name="WeekDefinitionCode")
	@JsonProperty("weekDefinitionCode")
	@Size(max=5)
	public String getWeekDefinitionCode() {
		return weekDefinitionCode;
	}

	public void setWeekDefinitionCode(String weekDefinitionCode) {
		this.weekDefinitionCode = weekDefinitionCode;
	}

	@Column(name="RatePeriodSequenceNumber", nullable=false)
	@JsonProperty("ratePeriodSequenceNumber")
	@NotNull
	@Max(99999)
	public int getRatePeriodSequenceNumber() {
		return ratePeriodSequenceNumber;
	}

	public void setRatePeriodSequenceNumber(int ratePeriodSequenceNumber) {
		this.ratePeriodSequenceNumber = ratePeriodSequenceNumber;
	}

	@Column(name="TypeOfCall", nullable = false)
	@JsonProperty("typeOfCall")
	@NotNull
	public String getTypeOfCall() {
		return typeOfCall;
	}

	public void setTypeOfCall(String typeOfCall) {
		this.typeOfCall = typeOfCall;
	}

	@Column(name="SpeacialTypeOfCall")
	@JsonProperty("speacialTypeOfCall")
	public String getSpeacialTypeOfCall() {
		return speacialTypeOfCall;
	}

	public void setSpeacialTypeOfCall(String speacialTypeOfCall) {
		this.speacialTypeOfCall = speacialTypeOfCall;
	}

	@Column(name="OriginatingDestinationCode", nullable=false)
	@JsonProperty("originatingDestinationCode")
	@NotNull
	@NotEmpty
	public String getOriginatingDestinationCode() {
		return originatingDestinationCode;
	}

	public void setOriginatingDestinationCode(String originatingDestinationCode) {
		this.originatingDestinationCode = originatingDestinationCode;
	}

	@Column(name="OriginatingSatelliteCode")
	@JsonProperty("originatingSatelliteCode")
	@Size(max=1)
	public String getOriginatingSatelliteCode() {
		return originatingSatelliteCode;
	}

	public void setOriginatingSatelliteCode(String originatingSatelliteCode) {
		this.originatingSatelliteCode = originatingSatelliteCode;
	}

	@Column(name="TerminatingDestinationCode", nullable=false)
	@JsonProperty("terminatingDestinationCode")
	@NotNull
	@NotEmpty
	public String getTerminatingDestinationCode() {
		return terminatingDestinationCode;
	}

	public void setTerminatingDestinationCode(String terminatingDestinationCode) {
		this.terminatingDestinationCode = terminatingDestinationCode;
	}

	@Column(name="TerminatingSatelliteCode")
	@JsonProperty("terminatingSatelliteCode")
	@Size(max=1)
	public String getTerminatingSatelliteCode() {
		return terminatingSatelliteCode;
	}

	public void setTerminatingSatelliteCode(String terminatingSatelliteCode) {
		this.terminatingSatelliteCode = terminatingSatelliteCode;
	}

	@Column(name="RateCapSequenceNumber", nullable=false)
	@JsonProperty("rateCapSequenceNumber")
	@NotNull
	public int getRateCapSequenceNumber() {
		return rateCapSequenceNumber;
	}

	public void setRateCapSequenceNumber(int rateCapSequenceNumber) {
		this.rateCapSequenceNumber = rateCapSequenceNumber;
	}

	@Column(name="CustomerSpecificRatingCode", nullable=false)
	@JsonProperty("customerSpecificRatingCode")
	@Size(max=8)
	public String getCustomerSpecificRatingCode() {
		return customerSpecificRatingCode;
	}

	public void setCustomerSpecificRatingCode(String customerSpecificRatingCode) {
		this.customerSpecificRatingCode = customerSpecificRatingCode;
	}

	@Column(name="TableEntryEffectiveDate", nullable=false)
	@JsonProperty("tableEntryEffectiveDate")
	@NotNull
	public Date getTableEntryEffectiveDate() {
		return tableEntryEffectiveDate;
	}

	public void setTableEntryEffectiveDate(Date tableEntryEffectiveDate) {
		this.tableEntryEffectiveDate = tableEntryEffectiveDate;
	}

	@Column(name="RateType", nullable=false)
	@JsonProperty("rateType")
	@NotEmpty
	@Size(max=1)
	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	@Column(name="Rate", nullable=false)
	@JsonProperty("rate")
	@NotNull
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Column(name="RateTimeUnit", nullable=false)
	@JsonProperty("rateTimeUnit")
	@NotEmpty
	public String getRateTimeUnit() {
		return rateTimeUnit;
	}

	public void setRateTimeUnit(String rateTimeUnit) {
		this.rateTimeUnit = rateTimeUnit;
	}

	@Column(name="MessageLevelPercentDiscount", nullable=false)
	@JsonProperty("messageLevelPercentDiscount")
	@NotNull
	public double getMessageLevelPercentDiscount() {
		return messageLevelPercentDiscount;
	}

	public void setMessageLevelPercentDiscount(
			double messageLevelPercentDiscount) {
		this.messageLevelPercentDiscount = messageLevelPercentDiscount;
	}

	@Column(name="CapThresholdCode")
	@JsonProperty("capThresholdCode")
	@Size(max=5)
	public String getCapThresholdCode() {
		return capThresholdCode;
	}

	public void setCapThresholdCode(String capThresholdCode) {
		this.capThresholdCode = capThresholdCode;
	}

	@Column(name="MinimumTime", nullable=false)
	@JsonProperty("minimumTime")
	@NotNull
	public int getMinimumTime() {
		return minimumTime;
	}

	public void setMinimumTime(int minimumTime) {
		this.minimumTime = minimumTime;
	}

	@Column(name="IncrementTime", nullable=false)
	@JsonProperty("incrementTime")
	public int getIncrementTime() {
		return incrementTime;
	}

	public void setIncrementTime(int incrementTime) {
		this.incrementTime = incrementTime;
	}

	@Column(name="IncrementTimeUnit", nullable=false)
	@JsonProperty("incrementTimeUnit")
	public String getIncrementTimeUnit() {
		return incrementTimeUnit;
	}

	public void setIncrementTimeUnit(String incrementTimeUnit) {
		this.incrementTimeUnit = incrementTimeUnit;
	}

	@Column(name="RateValidationIndicator", nullable=false)
	@JsonProperty("rateValidationIndicator")
	public String getRateValidationIndicator() {
		return rateValidationIndicator;
	}

	public void setRateValidationIndicator(String rateValidationIndicator) {
		this.rateValidationIndicator = rateValidationIndicator;
	}

	@Column(name="TableEntryExpirationDate", nullable=false)
	@JsonProperty("tableEntryExpirationDate")
	@NotNull
	public Date getTableEntryExpirationDate() {
		return tableEntryExpirationDate;
	}

	public void setTableEntryExpirationDate(Date tableEntryExpirationDate) {
		this.tableEntryExpirationDate = tableEntryExpirationDate;
	}

	@Column(name="TableEntryCreationDate", nullable=false)
	@JsonProperty("tableEntryCreationDate")
	public Date getTableEntryCreationDate() {
		return tableEntryCreationDate;
	}

	public void setTableEntryCreationDate(Date tableEntryCreationDate) {
		this.tableEntryCreationDate = tableEntryCreationDate;
	}

	@Column(name="TableEntryCreationUser", nullable=false)
	@JsonProperty("tableEntryCreationUser")
	public String getTableEntryCreationUser() {
		return tableEntryCreationUser;
	}

	public void setTableEntryCreationUser(String tableEntryCreationUser) {
		this.tableEntryCreationUser = tableEntryCreationUser;
	}

	@Column(name="Status", nullable=false)
	@JsonProperty("status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="CheckOutUser")
	@JsonProperty("checkOutUser")
	public String getCheckOutUser() {
		return checkOutUser;
	}

	public void setCheckOutUser(String checkOutUser) {
		this.checkOutUser = checkOutUser;
	}

	@Column(name="ApproveStatus", nullable=false)
	@JsonProperty("approveStatus")
	public int getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(int approveStatus) {
		this.approveStatus = approveStatus;
	}

	@Column(name="CBSSStatus", nullable=false)
	@JsonProperty("CBSSStatus")
	public int getCbssStatus() {
		return cbssStatus;
	}

	public void setCbssStatus(int cBSSStatus) {
		this.cbssStatus = cBSSStatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="toolPlan")
	@JsonIgnore
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="toolPlan")
	@JsonIgnore
	public Set<History> getHistories() {
		return histories;
	}

	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}
	
public String toHistoryString(){
		SimpleDateFormat smf = new SimpleDateFormat(FormatConst.EXPORT_FORMAT_DATE);
		DecimalFormat discountf = new DecimalFormat(FormatConst.DISCOUNT_NUMBER_FORMAT);
		DecimalFormat ratef = new DecimalFormat(FormatConst.RATE_NUMBER_FORMAT);
		DecimalFormat timeUnitf = new DecimalFormat(FormatConst.EXPORT_TIME_UNIT_FORMAT);
		DecimalFormat rateCapSeqf = new DecimalFormat(FormatConst.RATE_CAP_SEQ_FORMAT);
		String content = "";
		content += pricePlan.getPricePlanCode() + "\t";
		content += weekDefinitionCode + "\t";
		content += timeUnitf.format(ratePeriodSequenceNumber) + "\t";
		content += typeOfCall + "\t";
		content += speacialTypeOfCall + "\t";
		content += originatingDestinationCode + "\t";
		content += originatingSatelliteCode + "\t";
		content += terminatingDestinationCode + "\t";
		content += terminatingSatelliteCode + "\t";
		content += rateCapSeqf.format(rateCapSequenceNumber) + "\t";
		content += customerSpecificRatingCode + "\t";
		content += smf.format(tableEntryEffectiveDate) + "\t";
		content += rateType + "\t";
		content += ratef.format(rate) + "\t";
		content += rateTimeUnit + "\t";
		content += discountf.format(messageLevelPercentDiscount) + "\t";
		content += capThresholdCode + "\t";
		content += timeUnitf.format(minimumTime) + "\t";
		content += timeUnitf.format(incrementTime) + "\t";
		content += incrementTimeUnit + "\t";
		content += rateValidationIndicator + "\t";
		content += smf.format(tableEntryExpirationDate) + "\t";
		content += smf.format(tableEntryCreationDate) + "\n";
		return content;
	}
}
