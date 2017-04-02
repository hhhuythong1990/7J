package com.sevenj.web.data;

import java.util.Map;

import com.sevenj.web.common.SevenJUtils;

public class RateTimeUnitGenerator implements LookupDataGenerator {
	@Override
	public Map<String, String> generate() {
		String[] values = new String[] {"M","S","I"};
		return SevenJUtils.convertArrayToMap(values);
	}
}
