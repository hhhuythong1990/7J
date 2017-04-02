package com.sevenj.web.data;

import java.util.HashMap;
import java.util.Map;

import com.sevenj.model.ToolPlanStatus;

public class StatusGenerator implements LookupDataGenerator{

	@Override
	public Map<String, String> generate() {
		Map<String, String> map = new HashMap<String, String>();
		addValue(map,ToolPlanStatus.PENDING);
		addValue(map,ToolPlanStatus.APPROVED);
		addValue(map,ToolPlanStatus.REJECTED);
		return map;
	}
	
	private void addValue(Map<String, String> map, ToolPlanStatus status){
		map.put(status.getStatusCode()+"", status.getStatusName());
	}
}
