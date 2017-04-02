package com.sevenj.web.data;

import java.util.HashMap;
import java.util.Map;

import com.sevenj.web.common.SevenJUtils;

public class TypeOfCallGenerator implements LookupDataGenerator {
	@Override
	public Map<String, String> generate() {
		String[] values = new String[]{"0","3","7","8","D","S"};
		return SevenJUtils.convertArrayToMap(values);
	}
}
