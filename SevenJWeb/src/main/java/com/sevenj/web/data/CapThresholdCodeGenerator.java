package com.sevenj.web.data;

import java.util.Map;

import com.sevenj.web.common.SevenJUtils;

public class CapThresholdCodeGenerator implements LookupDataGenerator{

	@Override
	public Map<String, String> generate() {
		String[] values = new String[] {"FRST","OPT","SO1","SO2","CT","LBCR","TC","FRMPP","FRDL",
				"BOTP1","BOTP2","BOTP3","BOTP4","BDVOB","CANUS","BDVOR","BDVQR",
				"FRMW","FRRW","FRHW","UNLP","CABT","UNLC","UNOVS","UNINL","FRDM"};
		return SevenJUtils.convertArrayToMap(values);
	}

}
