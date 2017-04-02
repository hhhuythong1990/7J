package com.sevenj.web.util;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;


public class Utilities {
	
	public static String getStrDestByCode(int code) throws Exception
	{
		String result = "";
		String []data = {"Eligible","Not eligible","Not Applicable","Account","Line","AA2","AD1",
			          "AD5","AD9","AE4","AE8","AF5","AG1","AK1","AL1","AM1","AV5","AV9","AX0","AX1",
			          "AX3","AX4","AX5","AX6","AX7","AX8","AY1","AY2","AY4","AY5","AY7","AY9","AZ2","AZ6",
			          "AZ8","AZ9","BA1","BA3","BA6","BG2","BG3","BG6","BG8","BG9","BH0","BH1","BH2","BH3","BH6",
			          "FA3","Tool","Product","6 Rows on Flex with Rate Type P for all countries to receive the MSA Base Rates minus 40%","Top 28 & 29+ on Flex with Base Rates",
			          "4 Rows on Flex with Rate Type C for all countries to receive the MSA Base rates","Top 28 on Flex with Base Rates","Canada and US",
			          "DDD, Can & US 25? - all other DDD and Overseas are MSA Base rates","Top 28 & 29+ Overseas only","Can, US & Top 28 & 29+ Overseas","120 mins",
			       	"250 mins","300 mins","1200 mins","1500 mins","45000 mins","$20 that is tied to the MRC of $12.95","$25.00 ","250 min blocks",
			       	"100 minutes Free Airtime","250 minutes Free Airtime","Overseas messages only","Domestic and US messages only","Overseas messages only","Domestic and US messages only",
			       	"Overseas messages only","Domestic and US messages only","Overseas messages only","Domestic and US messages only","Overseas messages only","Domestic and US messages only"};
		
		for(int i=0;i<data.length;i++){
            result = data[i];
            if(code == i+1){  
                  return result;
            }
      }
		return result;
	}
	
	public static String getValueDestByCode(int code) throws Exception
	{
		String result = "";
		if(code == 1){
			result = "V";
		}else if(code == 2){
			result = "No";
		}else{
			result = getStrDestByCode(code);
		}
		return result;
	}
	
	public static String getValueDestByCodeViewWeb(int code) throws Exception
	{
		String result = "";
		if(code == 1){
			result = "V";
		}else if(code == 2){
			result = "No";
		}else{
			result = getStrDestByCode(code);
		}
		return result;
	}
	
	public static String formatNum_Grid(double dInput) {
		NumberFormat df1 = NumberFormat.getInstance(Locale.GERMAN);
		// DecimalFormat df1 = new DecimalFormat("#,###.###");
		String returnValue = df1.format(dInput);
		return returnValue;

	}
	
	public String getDayCur() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		return this.getTwoInt(day);
	}

	public String getMonthCur() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return this.getTwoInt(month);
	}

	public String getYearCur() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}

	public String getHourCur() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return this.getTwoInt(hour);
	}

	public String getMinuteCur() {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.MINUTE);
		return this.getTwoInt(minute);
	}

	public String getSecondsCur() {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.SECOND);
		return this.getTwoInt(minute);
	}
	
	public String getMillisecondsCur() {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.MILLISECOND);
		return getTwoInt(minute);
	}
	
	public String getDayMonthYearCur(){
		return getDayCur( ) + "-" + getMonthCur( ) + "-" + getYearCur();  
	}
	
	public String getYearMonthDayCur(){
		return getYearCur() + "-" + getMonthCur( ) + "-" + getDayCur( );  
	}
	
	public String getTwoInt(int iNumber) {
		String sNumber = String.valueOf(iNumber);
		if (sNumber.length() < 2) {
			return "0" + sNumber;
		}
		return sNumber;
	}
}
