package com.sevenj.web.model.converter;

import com.sevenj.model.ToolPlan;
import com.sevenj.web.model.ToolPlanModel;
import com.sevenj.web.util.Utilities;

public class ToolPlanModelConverter implements ViewModelConverter<ToolPlan, ToolPlanModel>{

//	Utilities util = new Utilities();
	
	@Override
	public ToolPlanModel convertToViewModel(ToolPlan businessModel) throws Exception {
		ToolPlanModel objModel = new ToolPlanModel();
		objModel.setID(businessModel.getID());
		objModel.setPricePlan(businessModel.getPricePlan().getPricePlanCode());//(obj.getPricePlanCode());
		objModel.setWeekDefinitionCode(businessModel.getWeekDefinitionCode());
		objModel.setRatePeriodSequenceNumber(String.valueOf(businessModel.getRatePeriodSequenceNumber()));
		objModel.setTypeOfCall(businessModel.getTypeOfCall());
		objModel.setSpeacialTypeOfCall(businessModel.getSpeacialTypeOfCall());
		objModel.setOriginatingDestinationCode(businessModel.getOriginatingDestinationCode());
		objModel.setOriginatingSatelliteCode(businessModel.getOriginatingSatelliteCode());
		objModel.setTerminatingDestinationCode(businessModel.getTerminatingDestinationCode());
		objModel.setTerminatingSatelliteCode(businessModel.getTerminatingSatelliteCode());
		objModel.setRateCapSequenceNumber(String.valueOf(businessModel.getRateCapSequenceNumber()));
		objModel.setCustomerSpecificRatingCode(businessModel.getCustomerSpecificRatingCode());
		objModel.setTableEntryEffectiveDate(businessModel.getTableEntryEffectiveDate());
		objModel.setRateType(businessModel.getRateType());
		objModel.setRate(String.valueOf(businessModel.getRate()));
		objModel.setRateTimeUnit(businessModel.getRateTimeUnit());
		objModel.setMessageLevelPercentDiscount(String.valueOf(businessModel.getMessageLevelPercentDiscount()));
		objModel.setCapThresholdCode(businessModel.getCapThresholdCode());
		objModel.setMinimumTime(String.valueOf(businessModel.getMinimumTime()));
		objModel.setIncrementTime(String.valueOf(businessModel.getIncrementTime()));
		objModel.setIncrementTimeUnit(businessModel.getIncrementTimeUnit());
		objModel.setRateValidationIndicator(businessModel.getRateValidationIndicator());
		objModel.setTableEntryExpirationDate(businessModel.getTableEntryExpirationDate());
		objModel.setTableEntryCreationDate(businessModel.getTableEntryCreationDate());
		objModel.setTableEntryCreationUser(businessModel.getTableEntryCreationUser());
		objModel.setStatus(String.valueOf(businessModel.getStatus()));
		objModel.setCheckOutUser(businessModel.getCheckOutUser());
		objModel.setApproveStatus(String.valueOf(businessModel.getApproveStatus()));
		objModel.setCbssStatus(String.valueOf(businessModel.getCbssStatus()));
		return objModel;
	}

	@Override
	public ToolPlan convertToBusinessModel(
			ToolPlanModel viewModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
  
}
