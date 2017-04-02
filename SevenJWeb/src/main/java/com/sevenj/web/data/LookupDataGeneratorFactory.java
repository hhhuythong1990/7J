package com.sevenj.web.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sevenj.web.common.ModelConst;

public class LookupDataGeneratorFactory {
	private static LookupDataGeneratorFactory instance = new LookupDataGeneratorFactory();
	private Map<String, Class<? extends LookupDataGenerator>> generators = new HashMap<String, Class<? extends LookupDataGenerator>>();
	private Logger logger = Logger.getLogger(LookupDataGeneratorFactory.class);
	
	public static LookupDataGeneratorFactory getInstance(){
		return instance;
	}
	
	public LookupDataGeneratorFactory(){
		initialize();
	}
	
	public LookupDataGenerator getGenerator(String name){
		LookupDataGenerator generator = null;
		try{
			if (generators.containsKey(name)){
				generator = generators.get(name).newInstance();
			}
		}catch(Exception ex){
			logger.error("Cannot get generator, caused by", ex);
		}
		return generator;
	}
	
	private void initialize(){
		generators.put(ModelConst.SATELLITE_CODES, SatelliteCodeGenerator.class);
		generators.put(ModelConst.TYPE_OF_CALLS, TypeOfCallGenerator.class);
		generators.put(ModelConst.SPECIAL_TYPE_OF_CALLS, SpecialTypeOfCallGenerator.class);
		generators.put(ModelConst.SPECIAL_TYPE_OF_CALLS, SpecialTypeOfCallGenerator.class);
		generators.put(ModelConst.CUSTOMER_SPECIFIC_RATING_CODES, CustomeSpecificRatingCodeGenerator.class);
		generators.put(ModelConst.CAP_THRESHOLD_CODE, CapThresholdCodeGenerator.class);
		generators.put(ModelConst.RATE_TYPE, RateTypeGenerator.class);
		generators.put(ModelConst.RATE_TIME_UNIT, RateTimeUnitGenerator.class);
		generators.put(ModelConst.WEEK_DEFINITION_CODES, WeekDefinitionCodeGenerator.class);
		generators.put(ModelConst.TOOL_PLAN_STATUSES, StatusGenerator.class);
		generators.put(ModelConst.PRICE_PLAN_CODE, PricePlanCodeGenerator.class);
		generators.put(ModelConst.PRICE_PLAN_NAME, PricePlanCodeGenerator.class);
	}
}
