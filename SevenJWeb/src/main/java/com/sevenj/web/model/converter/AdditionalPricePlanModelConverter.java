package com.sevenj.web.model.converter;

import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.web.model.AdditionalPricePlanViewModel;
import com.sevenj.web.model.KeyValue;
import com.sevenj.web.util.Utilities;

public class AdditionalPricePlanModelConverter implements ViewModelConverter<AdditionalPricePlan, AdditionalPricePlanViewModel>{

	Utilities util = new Utilities();
	
	@SuppressWarnings("static-access")
	@Override
	public AdditionalPricePlanViewModel convertToViewModel(
			AdditionalPricePlan businessModel) throws Exception {
		AdditionalPricePlanViewModel objModel = new AdditionalPricePlanViewModel();
		objModel.setPricePlanCode(businessModel.getPricePlanCode());
		objModel.setPricePlanName(businessModel.getPricePlanName());
		objModel.setCreationDate(businessModel.getCreationDate());
		objModel.setCreationUser(businessModel.getCreationUser());
		objModel.setAccountLevel(new KeyValue<Integer, String>(businessModel.getAccountLevel(), util.getStrDestByCode(businessModel.getAccountLevel())));
		objModel.setPrioprityCode(new KeyValue<Integer, String>(businessModel.getPriorityCode(), util.getStrDestByCode(businessModel.getPriorityCode())));
		objModel.setUsoc(businessModel.getUsoc());
		objModel.setIsToolPricingPlan(new KeyValue<Integer, String>( businessModel.getIsToolPricingPlan(), util.getStrDestByCode(businessModel.getIsToolPricingPlan())));
		objModel.setNoOverseaDest(new KeyValue<Integer, String>( businessModel.getNoOverseaDest(), util.getStrDestByCode(businessModel.getNoOverseaDest())));
		objModel.setTop28OverseaDest(new KeyValue<Integer, String>( businessModel.getTop28OverseaDest(), util.getStrDestByCode(businessModel.getTop28OverseaDest())));
		objModel.setT29OverseaDest(new KeyValue<Integer, String>( businessModel.getT29OverseaDest(), util.getStrDestByCode(businessModel.getT29OverseaDest())));
		objModel.setBlockOfTime(new KeyValue<Integer, String>( businessModel.getBlockOfTime(), util.getStrDestByCode(businessModel.getBlockOfTime())));
		objModel.setDollarCap(new KeyValue<Integer, String>( businessModel.getDollarCap(), util.getStrDestByCode(businessModel.getDollarCap())));
		objModel.setQuantity(new KeyValue<Integer, String>( businessModel.getQuantity() , util.getStrDestByCode(businessModel.getQuantity())));
		objModel.setCostCompareOnRule(new KeyValue<Integer, String>( businessModel.getQuantity(), util.getStrDestByCode(businessModel.getCostCompareOnRule())));
		objModel.setShortHaulRule(new KeyValue<Integer, String>(businessModel.getShortHaulRule(), util.getStrDestByCode(businessModel.getShortHaulRule())));
		objModel.setMultiRateSchedule(new KeyValue<Integer, String>(businessModel.getShortHaulRule(), util.getStrDestByCode(businessModel.getShortHaulRule())));
		objModel.setMultiRateSchedule(new KeyValue<Integer, String>(businessModel.getMultiRateSchedule(), util.getStrDestByCode(businessModel.getMultiRateSchedule())));
		objModel.setAirTime(new KeyValue<Integer, String>(businessModel.getAirTime(), util.getStrDestByCode(businessModel.getAirTime())));
		objModel.setVolumeDiscount(new KeyValue<Integer, String>(businessModel.getVolumeDiscount(), util.getStrDestByCode(businessModel.getVolumeDiscount())));
		objModel.setContractDiscount(new KeyValue<Integer, String>(businessModel.getContractDiscount(), util.getStrDestByCode(businessModel.getContractDiscount())));
		objModel.setIncrements60(new KeyValue<Integer, String>(businessModel.getIncrements60(), util.getStrDestByCode(businessModel.getIncrements60())));
		objModel.setIncrements60or30(new KeyValue<Integer, String>(businessModel.getIncrements60or30(), util.getStrDestByCode(businessModel.getIncrements60or30())));
		objModel.setIncrements30(new KeyValue<Integer, String>(businessModel.getIncrements30(), util.getStrDestByCode(businessModel.getIncrements30())));
		objModel.setIncrements10(new KeyValue<Integer, String>(businessModel.getIncrements10(), util.getStrDestByCode(businessModel.getIncrements10())));
		objModel.setIncrements1(new KeyValue<Integer, String>(businessModel.getIncrements1(), util.getStrDestByCode(businessModel.getIncrements1())));
		objModel.setPeakOffPeak(new KeyValue<Integer, String>(businessModel.getPeakOffPeak(), util.getStrDestByCode(businessModel.getPeakOffPeak())));
		objModel.setIs247(new KeyValue<Integer, String>(businessModel.getIs247(), util.getStrDestByCode(businessModel.getIs247())));
		objModel.setThd1(new KeyValue<Integer, String>(businessModel.getThd1(), util.getStrDestByCode(businessModel.getThd1())));
		objModel.setBillMinium(new KeyValue<Integer, String>(businessModel.getBillMinium(), util.getStrDestByCode(businessModel.getBillMinium())));
		objModel.setDescription(businessModel.getDescription());
		objModel.setLineLevel(new KeyValue<Integer, String>(businessModel.getLineLevel(), util.getStrDestByCode(businessModel.getLineLevel())));
		objModel.setProvincialOntarioOnly(new KeyValue<Integer, String>(businessModel.getProvincialOntarioOnly(), util.getStrDestByCode(businessModel.getProvincialOntarioOnly())));
		objModel.setProvincialQuebecOnly(new KeyValue<Integer, String>(businessModel.getProvincialQuebecOnly(), util.getStrDestByCode(businessModel.getProvincialQuebecOnly())));
		objModel.setPlanType(new KeyValue<Integer, String>(businessModel.getPlanType(), util.getStrDestByCode(businessModel.getPlanType())));
		return objModel;
	}

	@Override
	public AdditionalPricePlan convertToBusinessModel(
			AdditionalPricePlanViewModel viewModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
  
}
