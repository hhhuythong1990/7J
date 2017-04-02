package com.sevenj.web.data;

import java.util.HashMap;
import java.util.Map;

import com.sevenj.web.common.SevenJUtils;

public class SpecialTypeOfCallGenerator implements LookupDataGenerator {
	@Override
	public Map<String, String> generate() {
		String[] values = new String[] {"CC","FC","CCCD","FCCD","SVCD"};
		return SevenJUtils.convertArrayToMap(values);
	}
}
