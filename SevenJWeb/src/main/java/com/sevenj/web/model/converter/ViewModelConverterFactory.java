package com.sevenj.web.model.converter;

import java.util.HashMap;
import java.util.Map;

import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.ToolPlan;

public class ViewModelConverterFactory {
	@SuppressWarnings("rawtypes")
	private Map<String, ViewModelConverter> converters;
	private static ViewModelConverterFactory instance = new ViewModelConverterFactory();

	public static ViewModelConverterFactory getInstance() {
		return instance;
	}

	public ViewModelConverterFactory() {
		initialize();
	}

	@SuppressWarnings("rawtypes")
	private void initialize() {
		converters = new HashMap<String, ViewModelConverter>();
		converters.put(AdditionalPricePlan.class.getName(), new AdditionalPricePlanModelConverter());
		converters.put(ToolPlan.class.getName(), new ToolPlanModelConverter());
	}

	@SuppressWarnings("rawtypes")
	public ViewModelConverter getConverter(String name) {
		return converters.get(name);
	}
}
