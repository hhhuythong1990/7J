package com.sevenj.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sevenj.model.ToolPlan;
import com.sevenj.web.common.SevenJUtils;

public class ToolPlanValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ToolPlan.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ToolPlan toolPlan = (ToolPlan)target;
		String speacialTypeOfCall = toolPlan.getSpeacialTypeOfCall();
		String typeOfCall = toolPlan.getTypeOfCall();
		String originatingDestinationCode = toolPlan.getOriginatingDestinationCode();
		String originatingSatelliteCode = toolPlan.getOriginatingSatelliteCode();
		String terminatingDestinationCode = toolPlan.getTerminatingDestinationCode();
		String terminatingSatelliteCode = toolPlan.getTerminatingSatelliteCode();
		String capThresholdCode = toolPlan.getCapThresholdCode();
		int rateCapSequenceNumber = toolPlan.getRateCapSequenceNumber();
		String pricingPlanCode = toolPlan.getPricePlan().getPricePlanCode();
		String customerSpecificRatingCode = toolPlan.getCustomerSpecificRatingCode();
		String rateType = toolPlan.getRateType();
		double rate = toolPlan.getRate();
		double messageLevelPercentDiscount = toolPlan.getMessageLevelPercentDiscount();
		
		if(toolPlan.getMinimumTime() < 0 || toolPlan.getMinimumTime() > 60){
			errors.rejectValue("minimumTime", "error.minimumtime");
		}
		
		
		if(toolPlan.getIncrementTime() < 0 || toolPlan.getIncrementTime() > 60){
			errors.rejectValue("incrementTime", "error.incrementtime");
		}
		
		
		if(toolPlan.getRatePeriodSequenceNumber() <= 0){
			errors.rejectValue("ratePeriodSequenceNumber", "error.ratePeriodSequenceNumber");
		}
		
		if("FLAT".equals(toolPlan.getPricePlan().getPricePlanCode())){
			if(!"D".equals(toolPlan.getTypeOfCall())){
				errors.rejectValue("typeOfCall", "error.typeOfCallD");
			}
		}
		// Must be ‘SVCD’ if type-of-call is ‘S’
		if("S".equals(toolPlan.getTypeOfCall())){
			if(!"SVCD".equals(toolPlan.getSpeacialTypeOfCall())){
				errors.rejectValue("speacialTypeOfCall","error.speacialtypeofcallS");
			}
		}
		else
		{
			// Must be ‘CC,FC,CCCD,FCCD’ If type-of-call is ‘3’ or ‘7’
			if(toolPlan.getTypeOfCall().equals("3") || toolPlan.getTypeOfCall().equals("7")){
				if(!(speacialTypeOfCall.equals("CC") || speacialTypeOfCall.equals("FC") || speacialTypeOfCall.equals("CCCD") || speacialTypeOfCall.equals("FCCD"))){
					errors.rejectValue("speacialTypeOfCall", "error.speacialtypeofcall37");
				}
			}
			// Must be blank for other type-of-call values.
			else{
				if(!speacialTypeOfCall.isEmpty()){
					errors.rejectValue("speacialTypeOfCall","error.speacialtypeofcallBlank");
				}
			}
		}
		
		// Originating destination code
		// Must be blank if Type-of-call is ‘D’
		if(typeOfCall.equals("D")){
			if(!originatingDestinationCode.isEmpty()){
				errors.rejectValue("originatingDestinationCode","error.originatingDestinationCodeD");
			}
			// Originating-satellite-code
			// Must be blank if Type-of-call is ‘D’
			if(!originatingSatelliteCode.isEmpty()){
				errors.rejectValue("originatingSatelliteCode", "error.originatingSatelliteCodeD");
			}
			// Terminating-destination-code
			// Must be blank if Type-of-call is ‘D’
			if(!terminatingDestinationCode.isEmpty()){
				errors.rejectValue("terminatingDestinationCode", "error.terminatingDestinationCodeD");
			}
			// Terminating-satellite-code
			// Must be blank if Type-of-call is ‘D’
			if(!terminatingSatelliteCode.isEmpty()){
				errors.rejectValue("terminatingSatelliteCode","error.terminatingSatelliteCodeD");
			}
		}

		// Rate-Cap-sequence-number
		// Must be = 00 if CAP-threshold-code = blank
		// Must be > 00 if CAP-threshold-code not = blank
		if(capThresholdCode.isEmpty()){
			if(rateCapSequenceNumber != 0){
				errors.rejectValue("rateCapSequenceNumber","error.rateCapSequenceNumberBlank");
			}
		}else{
			if(rateCapSequenceNumber <= 0){
				errors.rejectValue("rateCapSequenceNumber","error.rateCapSequenceNumberNBlank");
			}
		}

		// Customer-specific-rating-code
		// Must be Blank if Pricing-Plan-Code is  not (FLAT or UNOV)
		if(!(pricingPlanCode.equals("FLAT") || pricingPlanCode.equals("UNOV"))){
			if(!customerSpecificRatingCode.isEmpty()){
				errors.rejectValue("customerSpecificRatingCode","error.customerSpecificRatingCodeMustBeBlank");
			}
		}
		
		// Must be Blank if Pricing-plan-code is ‘FLAT’ and Type-of-call is not ‘D’ and Originating-destination-code is ‘OVS’ and Terminating-destination-code is ‘CAN’
		if(pricingPlanCode.equals("FLAT") 
				&& !typeOfCall.equals("D") 
				&& originatingDestinationCode.equals("OVS") 
				&& terminatingDestinationCode.equals("CAN")){
			if(!customerSpecificRatingCode.isEmpty()){
				errors.rejectValue("customerSpecificRatingCode","error.customerSpecificRatingCodeMustBeBlankFLAT");
			}
		}
		
		// Must be ‘PMPL1’ or ‘PMPL2’ or ‘PMPL3’ or ‘PMPL4’ or ‘PMPL5’ if Pricing-plan-code is ‘FLAT’ and Type-of-call is not ‘D’ and Originating-destination-code is ‘CAN’ and Terminating-destination-code is numeric
		if(pricingPlanCode.equals("FLAT")
				&& !typeOfCall.equals("D")
				&& originatingDestinationCode.equals("CAN")
				&& SevenJUtils.isIntNumber(terminatingDestinationCode)){
			if(!customerSpecificRatingCode.startsWith("PMPL")){
				errors.rejectValue("customerSpecificRatingCode", "error.customerSpecificRatingCodePMPL");
			}
		}
		
		// Must be ‘PMTRA’ or ‘PMCAN’ or ‘PMUNS’ or ‘PMCCT’ or ‘PMCCC’ or ‘PMCCU’ if Pricing-plan-code is ‘FLAT’ and Type-of-call is ‘D’
		if(pricingPlanCode.equals("FLAT") && typeOfCall.equals("D")){
			if(!(!customerSpecificRatingCode.startsWith("PMPL") && customerSpecificRatingCode.startsWith("PM"))){
				errors.rejectValue("customerSpecificRatingCode","error.customerSpecificRatingCodePM");
			}
		}
		
		// Must be NOTINCL if Pricing-Plan-Code is  UNOV and Originating-destination-code = CAN and Terminating-destination-code is numeric
		if(pricingPlanCode.equals("UNOV") && originatingDestinationCode.equals("CAN") && SevenJUtils.isIntNumber(terminatingDestinationCode)){
			if(!customerSpecificRatingCode.equals("NOTINCL")){
				errors.rejectValue("customerSpecificRatingCode", "error.customerSpecificRatingCodeMustBeNOTINCL");
			}
		}
		
		// Must be INCL if Pricing-Plan-Code is  UNOV and Originating-destination-code = CAN and Terminating-destination-code is OVS
		if(pricingPlanCode.equals("UNOV") && originatingDestinationCode.equals("CAN") && terminatingDestinationCode.equals("OVS")){
			if(!customerSpecificRatingCode.equals("INCL")){
				errors.rejectValue("customerSpecificRatingCode","error.customerSpecificRatingCodeMustBeINCL");
			}
		}
		
		// Must be Blank if Pricing-Plan-Code is  UNOV and Originating-destination-code = (CAN or US or PRO) and Terminating-destination-code =  (CAN or US or PRO)
		if(pricingPlanCode.equals("UNOV") 
				&& (originatingDestinationCode.equals("CAN") || originatingDestinationCode.equals("US") || originatingDestinationCode.equals("PRO"))
				&& (terminatingDestinationCode.equals("CAN") || terminatingDestinationCode.equals("US") || terminatingDestinationCode.equals("PRO"))){
			if(!customerSpecificRatingCode.isEmpty()){
				errors.rejectValue("customerSpecificRatingCode","error.customerSpecificRatingCodeMustBeBlankUNOV");
			}
		}
		
		// Table entry effective date
		// TODO Must be > or = Table-entry-creation-date
		
		// Rate type
		// Must be F or C if Pricing-plan-code = TWC1 or PAY1 or CTR1
		if(pricingPlanCode.equals("TWC1") || pricingPlanCode.equals("PAY1") || pricingPlanCode.equals("CTR1")) {
			if(!(rateType.equals("F") || rateType.equals("C"))){
				errors.rejectValue("rateType","error.rateTypeFC");
			}
		}
		// Must be I  if Pricing-plan-code = (ODIN or FRMP or FRMD or GUY1 or HSLD or LEGO or DBLD or PAK1 or PAK2  or PAK3 or PAK4 or BDVQ or BDVO or BDVC or BDOB or FRDW or FRFW or FRGW or UNPP or CBOT or UNLD or UNOV or UNWO) AND            CAP-threshold-code not blank 
		if(!rateType.equals("I")){
			if(((pricingPlanCode.equals("ODIN") 
					|| pricingPlanCode.equals("FRMP") 
					|| pricingPlanCode.equals("GUY1")
					|| pricingPlanCode.equals("HSDL")
					|| pricingPlanCode.equals("LEGO")
					|| pricingPlanCode.equals("DBLD")
					|| pricingPlanCode.startsWith("PAK") // PAK1, PAK2, PAK3, PAK4
					|| pricingPlanCode.startsWith("BDV") // or BDVQ or BDVO or BDVC
					|| pricingPlanCode.equals("BDOB")
					|| pricingPlanCode.equals("FRDW")
					|| pricingPlanCode.equals("FRFW")
					|| pricingPlanCode.equals("FRGW")
					|| pricingPlanCode.equals("UNPP")
					|| pricingPlanCode.equals("CBOT")
					|| pricingPlanCode.equals("UNLD")
					|| pricingPlanCode.equals("UNOV")
					|| pricingPlanCode.equals("UNWO")
					) && !capThresholdCode.isEmpty())) {
				errors.rejectValue("rateType","error.rateTypeI");
			}
		}
		
		// Must be C if Pricing-plan-code is FLAT and Type-of-call is not D and Originating-destination-code is OVS AND Terminating-destination-code is  CAN
		if(!rateType.equals("C")){
			if((pricingPlanCode.equals("FLAT")
					&& originatingDestinationCode.equals("OVS")
					&& terminatingDestinationCode.equals("CAN"))){
				errors.rejectValue("rateType","error.rateTypeC");
			}
		}
		
		// Must be C if  Pricing-plan-code is (FLAT or HSLD) and Type-of-call is not D and Originating-destination-code is CAN and Terminating-destination-code is (036, 038, 064, 065, 899, 930, 931, 932, 933, 934, 935, 936, 937, 938 or 939)
		if(!rateType.equals("C")){
			if(((pricingPlanCode.equals("FLAT") || pricingPlanCode.equals("HSLD"))
					&& !typeOfCall.equals("D")
					&& originatingDestinationCode.equals("CAN")
					&& (terminatingDestinationCode.equals("036")
							|| terminatingDestinationCode.equals("038")
							|| terminatingDestinationCode.equals("064")
							|| terminatingDestinationCode.equals("065")
							|| terminatingDestinationCode.equals("899")
							|| terminatingDestinationCode.startsWith("93")))){
				errors.rejectValue("rateType","error.rateTypeC");
			}
		}
		
		// Rate
		// Must be = 0.00 if Rate-type = P or C
		if(rateType.equals("P") || rateType.equals("C")){
			if(rate != 0){
				errors.rejectValue("rate","error.rateMustBe0");
			}
		}
		
		// Must be > or = 0.00 if Rate-type =F  or I
		if(rateType.equals("F") || rateType.equals("I")){
			if(rate < 0){
				errors.rejectValue("rate","error.rateMustMore0");
			}
		}
		
		
		// Message-level-percent-discount
		// Must be = 0.00 if Rate-type = I or C or F
		if(rateType.equals("I") || rateType.equals("C") || rateType.equals("F")){
			if(messageLevelPercentDiscount != 0){
				errors.rejectValue("messageLevelPercentDiscount","error.messageLevelPercentDiscountMust0");
			}
		}
		
		// Must be > 0.00 if Rate-type = ‘P’
		if(rateType.equals("P")){
			if(messageLevelPercentDiscount <=0){
				errors.rejectValue("messageLevelPercentDiscount", "error.messageLevelPercentDiscountMore0");
			}
		}
		
		// Cap-threshold-code
		// FRST    (for ODIN)
		if(pricingPlanCode.equals("ODIN")){
			if(!capThresholdCode.equals("FRST")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeFRST");
			}
		}
		
		// OPT      (for  ADVP)
		if(pricingPlanCode.equals("ADVP")){
			if(!capThresholdCode.equals("OPT")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeOPT");
			}
		}
		// SO1      (for  WIR1)
		if(pricingPlanCode.equals("WIR1")){
			if(!capThresholdCode.equals("SO1")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeSO1");
			}
		}
		// SO2      (for  WIR2)
		if(pricingPlanCode.equals("WIR2")){
			if(!capThresholdCode.equals("SO2")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeSO2");
			}
		}
		// CT        (for  CTR1)
		if(pricingPlanCode.equals("CTR1")){
			if(!capThresholdCode.equals("CT")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeCT");
			}
		}
		// LBCR   (for  PAY1)
		if(pricingPlanCode.equals("PAY1")){
			if(!capThresholdCode.equals("LBCR")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeLBCR");
			}
		}
		// TC         (for  TWC1)
		if(pricingPlanCode.equals("TWC1")){
			if(!capThresholdCode.equals("TC")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeTC");
			}
		}
		// FRMPP (for FRMP)
		if(pricingPlanCode.equals("FRMP")){
			if(!capThresholdCode.equals("FRMPP")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeFRMPP");
			}
		}
		// FRDL    (for FRMD)
		if(pricingPlanCode.equals("FRMD")){
			if(!capThresholdCode.equals("FRDL")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeFRDL");
			}
		}
		// FRMN   (for GUY1)
		if(pricingPlanCode.equals("GUY1")){
			if(!capThresholdCode.equals("FRMN")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeFRMN");
			}
		}
		// HSEB    (for HSLD)
		if(pricingPlanCode.equals("HSDL")){
			if(!capThresholdCode.equals("HSEB")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeHSEB");
			}
		}
		// FRDM   (for LEGO)
		if(pricingPlanCode.equals("LEGO")){
			if(!capThresholdCode.equals("FRDM")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeFRDM");
			}
		}
		// DBLDD (for DBLD)
		if(pricingPlanCode.equals("DBLD")){
			if(!capThresholdCode.equals("DBLDD")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeDBLDD");
			}
		}
		// BOTP1  (for  PAK1
		if(pricingPlanCode.equals("PAK1")){
			if(!capThresholdCode.equals("BOTP1")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeBOTP1");
			}
		}
		// BOTP2  (for PAK2)
		if(pricingPlanCode.equals("PAK2")){
			if(!capThresholdCode.equals("BOTP2")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeBOTP2");
			}
		}
		// BOTP3  (for PAK3)
		if(pricingPlanCode.equals("PAK3")){
			if(!capThresholdCode.equals("BOTP3")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeBOTP3");
			}
		}
		// BOTP4  (for PAK4)
		if(pricingPlanCode.equals("PAK4")){
			if(!capThresholdCode.equals("BOTP4")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeBOTP4");
			}
		}
		// BDVOB (for BDOB)
		if(pricingPlanCode.equals("BDOB")){
			if(!capThresholdCode.equals("BDVOB")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeBDVOB");
			}
		}
		// CANUS (for BDVC)
		if(pricingPlanCode.equals("BDVC")){
			if(!capThresholdCode.equals("CANUS")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeCANUS");
			}
		}
		// BDVOR (for BDVO)
		if(pricingPlanCode.equals("BDVO")){
			if(!capThresholdCode.equals("BDVOR")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeBDVOR");
			}
		}
		// BDVQR (for BDVQ)
		if(pricingPlanCode.equals("BDVQ")){
			if(!capThresholdCode.equals("BDVQR")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeBDVQR");
			}
		}
		// FRMW (for FRDW)
		if(pricingPlanCode.equals("FRDW")){
			if(!capThresholdCode.equals("FRMW")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeFRMW");
			}
		}
		// FRRW (for FRFW)
		if(pricingPlanCode.equals("FRFW")){
			if(!capThresholdCode.equals("FRRW")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeFRRW");
			}
		}
		// FRHW (for FRGW)
		if(pricingPlanCode.equals("FRGW")){
			if(!capThresholdCode.equals("FRHW")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeFRHW");
			}
		}
		// UNLP (for UNPP)
		if(pricingPlanCode.equals("UNPP")){
			if(!capThresholdCode.equals("UNLP")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeUNLP");
			}
		}
		// CABT (for CBOT)
		if(pricingPlanCode.equals("CBOT")){
			if(!capThresholdCode.equals("CABT")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeCABT");
			}
		}
		
		// UNLC (for UNLD)
		if(pricingPlanCode.equals("UNLD")){
			if(!capThresholdCode.equals("UNLC")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeUNLC");
			}
		}
		// UNOVS (for UNOV
		if(pricingPlanCode.equals("UNOV")){
			if(!capThresholdCode.equals("UNOVS")){
				errors.rejectValue("capThresholdCode", "error.capThresholdCodeUNOVS");
			}
		}
		// UNINL (for UNWO)
		if(pricingPlanCode.equals("UNWO")){
			if(!capThresholdCode.equals("UNINL")){
				errors.rejectValue("capThresholdCode","error.capThresholdCodeUNINL");
			}
		}
	}
}
