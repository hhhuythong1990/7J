package com.sevenj.web.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeekDefinitionCodeGenerator implements LookupDataGenerator {
	@Override
	public Map<String, String> generate() {
		Map<String, String> weekDefinitionCodes = new LinkedHashMap<String, String>();
		for (int i = 0; i < 100; i++) {
			String codeValue = "RP";
			if (i < 10) {
				codeValue = codeValue + "0" + i;
			} else {
				codeValue = codeValue + i;
			}
			weekDefinitionCodes.put(codeValue, codeValue);
		}
		return weekDefinitionCodes;
	}
}
