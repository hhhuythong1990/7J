package com.sevenj.web.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.service.AdditionalPricePlanService;
import com.sevenj.service.impl.AdditionalPricePlanServiceImpl;
import com.sevenj.web.model.AdditionalPricePlanView;

public class AdditionalPricePlanValidator implements Validator {
	private Logger logger = Logger.getLogger(AdditionalPricePlanValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(AdditionalPricePlanView.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AdditionalPricePlanView additionalPricePlanView = (AdditionalPricePlanView) target;
		try {
			AdditionalPricePlanService service = new AdditionalPricePlanServiceImpl();
			AdditionalPricePlan additionalPricePlan = service.searchAdditonalPricePlan(additionalPricePlanView.getPricePlanCode());
			if (additionalPricePlan != null){
				errors.rejectValue("pricePlanCode", "additionalPricePlan.pricePlanCode.existed", new String[] {additionalPricePlanView.getPricePlanCode()}, "");
			}
		} catch (Exception ex) {
			logger.error("Cannot validate additional price plan", ex);
		}

	}

}
