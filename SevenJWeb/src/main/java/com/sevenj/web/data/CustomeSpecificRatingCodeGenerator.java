package com.sevenj.web.data;

import java.util.HashMap;
import java.util.Map;

import com.sevenj.web.common.SevenJUtils;

public class CustomeSpecificRatingCodeGenerator implements LookupDataGenerator {
	@Override
	public Map<String, String> generate() {
		String[] values = new String[]{"PMPL1","PMPL2","PMPL3","PMPL4","PMPL5","PMTRA","PMCAN","PMUNS","PMCCT","PMCCC","PMCCU","INCL","NOTINCL"};
		return SevenJUtils.convertArrayToMap(values);
	}
}
