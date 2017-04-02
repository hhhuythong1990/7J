package com.sevenj.web.data;

import java.util.HashMap;
import java.util.Map;

import com.sevenj.model.PricePlanStatus;
import com.sevenj.model.ToolPlanStatus;

public class PricePlanCodeGenerator implements LookupDataGenerator{

	@Override
	public Map<String, String> generate() {
		Map<String, String> map = new HashMap<String, String>();
		addValue(map,PricePlanStatus.NEW);
		addValue(map,PricePlanStatus.WAIT_FOR_APPROVE);
		addValue(map,PricePlanStatus.APPROVED);
		return map;
	}
	
	private void addValue(Map<String, String> map, PricePlanStatus status){
		map.put(status.getStatusName()+"", status.getStatusName());
	}
}
