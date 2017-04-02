package com.sevenj.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sevenj.model.PricePlan;

public class PricePlanEditValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return PricePlan.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PricePlan pricePlan = (PricePlan)target;
		if(pricePlan.getPricePlanCode()==null){
			errors.rejectValue("pricePlanCode", "error.pricePlanCodeRequired");
		}
		else if(pricePlan.getPricePlanCode().length()!=4 ){
			errors.rejectValue("pricePlanCode", "error.pricePlanCodeCharacters");
		}
		if(pricePlan.getPricePlanName()==null || pricePlan.getPricePlanName()==""){
			errors.rejectValue("pricePlanName", "error.pricePlanNameRequired");
		}
	}
}
