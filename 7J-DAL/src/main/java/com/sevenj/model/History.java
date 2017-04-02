package com.sevenj.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
@Entity
@Table(name="toolplanhistory")
public class History {
	private int ID;
	private ToolPlan toolPlan;
	private String toolPlanRecord;
	private int historyType;
	private Date historyDate;
	private String changeUser;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable = false)
	@JsonProperty("ID")
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	@ManyToOne
	@JoinColumn(name="ToolPlanID", nullable=false)
	@JsonIgnore
	public ToolPlan getToolPlan() {
		return toolPlan;
	}
	public void setToolPlan(ToolPlan toolPlan) {
		this.toolPlan = toolPlan;
	}
	@Column(name="ToolPlanRecord", nullable = true)
	@JsonProperty("ToolPlanRecord")
	public String getToolPlanRecord() {
		return toolPlanRecord;
	}
	public void setToolPlanRecord(String toolPlanRecord) {
		this.toolPlanRecord = toolPlanRecord;
	}
	
	@Column(name="HistoryType", nullable=false)
	@JsonProperty("HistoryType")
	public int getHistoryType() {
		return historyType;
	}
	public void setHistoryType(int historyType) {
		this.historyType = historyType;
	}
	
	@Column(name="HistoryDate", nullable = false)
	@JsonProperty("HistoryDate")
	public Date getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}
	
	@Column(name="ChangeUser", nullable = false)
	@JsonProperty("ChangeUser")
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	
	public ToolPlan parseHistory() throws Exception{
		ToolPlan resultToolPlan = new ToolPlan();
		SimpleDateFormat formatter = new SimpleDateFormat(FormatConst.EXPORT_FORMAT_DATE);
		String record[] = toolPlanRecord.split("\t");
		String pricingPlanCode = record[0];
		String weekDefinitionCode = record[1];
		int ratePeriodSequenceNumber = Integer.parseInt(record[2].trim());
		String typeOfCall = record[3];
		String speacialTypeOfCall = record[4];
		String originatingDestinationCode = record[5];
		String originatingSatelliteCode = record[6];
		String terminatingDestinationCode = record[7];
		String terminatingSatelliteCode = record[8];
		int rateCapSequenceNumber = Integer.parseInt(record[9].trim());
		String customerSpecificRatingCode = record[10];
		String tableEntryEffectiveDate = record[11];
		String rateType = record[12];
		double rate = Double.parseDouble(record[13].trim());
		String rateTimeUnit = record[14];
		double messageLevelPercentDiscount = Double.parseDouble(record[15]
				.trim());
		String capThresholdCode = record[16];
		int minimumTime = Integer.parseInt(record[17].trim());
		int incrementTime = Integer.parseInt(record[18].trim());
		String incrementTimeUnit = record[19];
		String rateValidationIndicator = record[20];
		String tableEntryExpirationDate = record[21];
		String tableEntryCreationDate = record[22];
		resultToolPlan.setID(toolPlan.getID());
		PricePlan pricePlan = new PricePlan();
		pricePlan.setPricePlanCode(pricingPlanCode);
		resultToolPlan.setPricePlan(pricePlan);
		resultToolPlan.setWeekDefinitionCode(weekDefinitionCode);
		resultToolPlan
				.setRatePeriodSequenceNumber(ratePeriodSequenceNumber);
		resultToolPlan.setTypeOfCall(typeOfCall);
		resultToolPlan.setSpeacialTypeOfCall(speacialTypeOfCall);
		resultToolPlan
				.setOriginatingDestinationCode(originatingDestinationCode);
		resultToolPlan
				.setOriginatingSatelliteCode(originatingSatelliteCode);
		resultToolPlan
				.setTerminatingDestinationCode(terminatingDestinationCode);
		resultToolPlan
				.setTerminatingSatelliteCode(terminatingSatelliteCode);
		resultToolPlan.setRateCapSequenceNumber(rateCapSequenceNumber);
		resultToolPlan
				.setCustomerSpecificRatingCode(customerSpecificRatingCode);
		resultToolPlan.setTableEntryEffectiveDate(new Date(formatter.parse(
				tableEntryEffectiveDate).getTime()));
		resultToolPlan.setRateType(rateType);
		resultToolPlan.setRate(rate);
		resultToolPlan.setRateTimeUnit(rateTimeUnit);
		resultToolPlan
				.setMessageLevelPercentDiscount(messageLevelPercentDiscount);
		resultToolPlan.setCapThresholdCode(capThresholdCode);
		resultToolPlan.setMinimumTime(minimumTime);
		resultToolPlan.setIncrementTime(incrementTime);
		resultToolPlan.setIncrementTimeUnit(incrementTimeUnit);
		resultToolPlan.setRateValidationIndicator(rateValidationIndicator);
		resultToolPlan.setTableEntryExpirationDate(new Date(formatter
				.parse(tableEntryExpirationDate).getTime()));
		resultToolPlan.setTableEntryCreationDate(new Date(formatter
				.parse(tableEntryCreationDate).getTime()));
		return resultToolPlan;
	}
}
