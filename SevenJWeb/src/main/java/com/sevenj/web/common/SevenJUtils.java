package com.sevenj.web.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SevenJUtils {
	public static Map<String, String> convertArrayToMap(String[] values) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String value : values) {
			map.put(value, value);
		}
		return map;
	}
	
	public static Date convertString2Date(String dateString){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
		}
		return date;
	}
	
	public static boolean isIntNumber(String num){
		try{
			Integer.parseInt(num);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
