package com.sevenj.web.data;

import java.util.Map;

import com.sevenj.web.common.SevenJUtils;

public class RateTypeGenerator implements LookupDataGenerator {
	@Override
	public Map<String, String> generate() {
		String[] values = new String[] {"I","P","F","C"};
		return SevenJUtils.convertArrayToMap(values);
	}
}
