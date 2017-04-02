package com.sevenj.web.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SatelliteCodeGenerator implements LookupDataGenerator{

	@Override
	public Map<String, String> generate() {
		Map<String, String> data = new LinkedHashMap<String, String>();
		for(int i=0; i < 10; i++){
			data.put(""+i, ""+i);
		}
		data.put("M", "M");
		return data;
	}

}
