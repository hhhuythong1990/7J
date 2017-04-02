package com.sevenj.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sevenj.model.PricePlan;
import com.sevenj.service.PricePlanService;
import com.sevenj.service.impl.PricePlanServiceImpl;

public class PricePlanCreateValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return PricePlan.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PricePlan pricePlan = (PricePlan)target;
		PricePlanService pricePlanService= new PricePlanServiceImpl();
		PricePlan existedPricePlan =null;
		try {
			existedPricePlan = pricePlanService.getPricePlan(pricePlan.getPricePlanCode());
			if(existedPricePlan!=null){
				errors.rejectValue("pricePlanCode", "error.pricePlanCodeExists");
			}
		} catch (Exception e) {
		}
		
//		if(pricePlan.getPricePlanCode() == null){
//			errors.rejectValue("pricePlanCode", "error.pricePlanCodeRequired");
//		}
//		else if(pricePlan.getPricePlanCode().length()!=4 ){
//			errors.rejectValue("pricePlanCode", "error.pricePlanCodeCharacters");
//		}else if(existedPricePlan!=null){
//			errors.rejectValue("pricePlanCode", "error.pricePlanCodeExists");
//		}
//		if(pricePlan.getPricePlanName() == null){
//			errors.rejectValue("pricePlanName", "error.pricePlanNameRequired");
//		}
	}
}
