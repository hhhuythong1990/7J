package com.sevenj.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.sevenj.dal.dao.AdditionalPricePlanDAO;
import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.service.IReportService;
import com.sevenj.service.util.ServiceUtils;

public class ADDPricePlanReportServiceImpl  implements IReportService<AdditionalPricePlan>{

	private Logger logger = Logger.getLogger(ADDPricePlanReportServiceImpl.class.getName());
	AdditionalPricePlanDAO dao;
	ServiceUtils serviceUtil = new ServiceUtils();
	
	@SuppressWarnings({ "static-access" })
	@Override
	public HSSFWorkbook exportExcel(List<AdditionalPricePlan> list) throws Exception {
		/**
         * 	PROCESS EXCEL
         */
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyleTitle, cellStyleRecordYellow, cellStyleRecordRed, cellStyleYellowColorDes, cellStyleredColorDes, cellStyleBorder, cellStyleBackgroundColor = null;
		HSSFFont fontTitle, fontRowContent;
		HSSFSheet sheet = workbook.createSheet("Additional Price Plan Info");
		
		// font tilte
		fontTitle = workbook.createFont();
		fontTitle.setFontName("Arial");
		fontTitle.setFontHeightInPoints((short) 8);
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// font row
		fontRowContent = workbook.createFont();
		fontRowContent.setFontName("Arial");
		fontRowContent.setFontHeightInPoints((short) 8);
		fontRowContent.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		cellStyleBorder = workbook.createCellStyle();
		setBorderLine(cellStyleBorder);
		cellStyleBorder.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyleBorder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleBorder.setFont(fontTitle);
		
		// bgcolor
		cellStyleBackgroundColor = workbook.createCellStyle();
		setBorderLine(cellStyleBackgroundColor);
		cellStyleBackgroundColor.setFont(fontRowContent);
		
		// background color yellow
		cellStyleRecordYellow = workbook.createCellStyle();
		cellStyleRecordYellow.setFillForegroundColor(HSSFColor.YELLOW.index);
		cellStyleRecordYellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setBorderLine(cellStyleRecordYellow);
		cellStyleRecordYellow.setFont(fontRowContent);
		
		// background color red
		cellStyleRecordRed = workbook.createCellStyle();
		HSSFColor lightRed =  setColor(workbook,(byte) 255, (byte)102,(byte) 0);
		setBackgroundColor(cellStyleRecordRed, lightRed);
		setBorderLine(cellStyleRecordRed);
		cellStyleRecordRed.setFont(fontRowContent);
		
		// Create Title Excel
		HSSFRow row = sheet.createRow((short) 10);
		HSSFCell cell;
		int stt = 1;
        int r = 6;
        
		cellStyleTitle = workbook.createCellStyle();
		HSSFColor colorTitle =  setColor(workbook,(byte) 192, (byte)192,(byte) 192);
		setBackgroundColor(cellStyleTitle, colorTitle);
		cellStyleTitle.setFont(fontTitle);
		cellStyleTitle.setAlignment((short) 0);
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitle.setWrapText(true);   //Wrapping text
		setBorderLine(cellStyleTitle);
		
		
		cellStyleYellowColorDes = workbook.createCellStyle();
		HSSFColor yellow =  setColor(workbook,(byte) 255, (byte)255,(byte) 0);
		setBackgroundColor(cellStyleYellowColorDes, yellow);
		
		cellStyleredColorDes = workbook.createCellStyle();
		HSSFColor Red =  setColor(workbook,(byte) 255, (byte)102,(byte) 0);
		setBackgroundColor(cellStyleredColorDes, Red);
		
		setWidthColumn(sheet);
		
		/*
         * Create EXCEL
         */
		setRowNoteData(cellStyleYellowColorDes, cellStyleredColorDes, cellStyleBorder, sheet);
		
		// Create Title Excel
		String[] rowTitle = {"Pricing Plan Code","Pricing Plan Name","Account Level","Line Level","Prioprity Code","USOC","Tool Pricing Plan","No Overseas Destinations","Top 28 Overseas Destinations ONLY","Top 28 & 29+ Overseas Destinations","Block of Time","Dollar CAP","Bill Minimum","Quantity of Blocks","Short Haul Rule","Cost Comparison Rule","Calling Card Surcharges","Multiple Rate Schedules","Airtime","Volume Discount","Contract Discount","60/60 increments ONLY","60/60 or 30/6 increments","30/6 increments","10/10 increments","1/1 increments","Peak/Off-Peak","24/7 Time Frame","TDH1 - Handicap Discounts","Provincial Ontario Only","Provincial Quebec Only"};
		row = sheet.createRow((short) r);
		row.setHeight((short) 450);
		cell = row.createCell(0);
		cell.setCellValue(getStringCell(""));

		for(int i=0;i<rowTitle.length;i++){
  		  cell = row.createCell(i+1);
  		  cell.setCellValue(getStringCell(rowTitle[i]));
  		  cell.setCellStyle(cellStyleTitle);
  	  	}

		/**
		 * 	I./ LOOP DATA:
		 */
		
        if(list != null && list.size() >0){
        	for (AdditionalPricePlan obj : list) {
        		String pricePlanName = "", prioprityCode = "", usoc = "", pricePlanCode = "", lineLevel, 
        				sAccountLevel = "", sIsToolPricingPlan = "", sNoOverseaDest = "", sTop28OverseaDest = "", sT29OverseaDest = "",
    					sBlockOfTime = "", sDollarCap="", sBillMinium="", sQuantity = "", sShortHaulRule = "", sCostCompareOnRule, 
    					sWithSurcharges="", sMultiRateSchedule="", sAirTime="",
    					sVolumeDiscount="", sContractDiscount="", sIncrements60="", sIncrements60or30="", sIncrements30="",
    					sIncrements10="", sIncrements1="", sPeakOffPeak="", sIs247="", sThd1="", provincialOntarioOnly = "", provincialQuebecOnly ="";
        		
        		if(obj.getPlanType() == 4){ // Consumer
        			cellStyleBackgroundColor = cellStyleRecordRed;
        		}else if(obj.getPlanType() == 3){ // Business
        			cellStyleBackgroundColor = cellStyleRecordYellow;
        		}
        		if(obj.getPricePlanName()!= null && !obj.getPricePlanName().equals("")){
        			pricePlanName = obj.getPricePlanName();
        		}
        		
        		if(obj.getPricePlanCode() != null && !obj.getPricePlanCode().equals("")){
        			pricePlanCode = obj.getPricePlanCode();
        		}
        		
    			sAccountLevel = serviceUtil.getValueDestByCode(obj.getAccountLevel());
    			lineLevel = serviceUtil.getValueDestByCode(obj.getLineLevel());
    			
    			if(obj.getPricePlanCode() != null && !obj.getPricePlanCode().equals("")){
        			prioprityCode = obj.getPricePlanCode();
        		}
        		
        		if(obj.getUsoc() != null && !obj.getUsoc().equals("")){
        			usoc = obj.getUsoc();
        		}
        		
    			sIsToolPricingPlan = serviceUtil.getValueDestByCode(obj.getIsToolPricingPlan());
        		sNoOverseaDest = serviceUtil.getValueDestByCode(obj.getNoOverseaDest());
        		sTop28OverseaDest = serviceUtil.getValueDestByCode(obj.getTop28OverseaDest());
        		sT29OverseaDest = serviceUtil.getValueDestByCode(obj.getT29OverseaDest());
        		sBlockOfTime = serviceUtil.getValueDestByCode(obj.getBlockOfTime());
        		sDollarCap = serviceUtil.getValueDestByCode(obj.getDollarCap());
				sBillMinium = serviceUtil.getValueDestByCode(obj.getBillMinium());
    			sQuantity = serviceUtil.getValueDestByCode(obj.getQuantity());
    			
    			sShortHaulRule = serviceUtil.getValueDestByCode(obj.getShortHaulRule());
    			sCostCompareOnRule = serviceUtil.getValueDestByCode(obj.getCostCompareOnRule());
    			sWithSurcharges = serviceUtil.getValueDestByCode(obj.getWithSurcharges());
    			sMultiRateSchedule = serviceUtil.getValueDestByCode(obj.getMultiRateSchedule());
    			sAirTime = serviceUtil.getValueDestByCode(obj.getAirTime());
    			sVolumeDiscount = serviceUtil.getValueDestByCode(obj.getVolumeDiscount());
    			sContractDiscount = serviceUtil.getValueDestByCode(obj.getContractDiscount());
    			sIncrements60 = serviceUtil.getValueDestByCode(obj.getIncrements60());
    			sIncrements60or30 = serviceUtil.getValueDestByCode(obj.getIncrements60or30());
    			sIncrements30 = serviceUtil.getValueDestByCode(obj.getIncrements30());
    			sIncrements10 = serviceUtil.getValueDestByCode(obj.getIncrements10());
    			sIncrements1 = serviceUtil.getValueDestByCode(obj.getIncrements1());
    			sPeakOffPeak = serviceUtil.getValueDestByCode(obj.getPeakOffPeak());
    			sIs247 = serviceUtil.getValueDestByCode(obj.getIs247());
    			sThd1 = serviceUtil.getValueDestByCode(obj.getThd1());
    			provincialOntarioOnly =  serviceUtil.getValueDestByCode(obj.getProvincialOntarioOnly());
    			provincialQuebecOnly =  serviceUtil.getValueDestByCode(obj.getProvincialQuebecOnly());
        		
                row = sheet.createRow((r + 1));
                
                cell = row.createCell(0);
                cell.setCellValue(stt);
                cell.setCellStyle(cellStyleBorder);
                
                setValueRow(cellStyleBackgroundColor, row, pricePlanName, prioprityCode, usoc,
						pricePlanCode, lineLevel, sAccountLevel,
						sIsToolPricingPlan, sNoOverseaDest, sTop28OverseaDest,
						sT29OverseaDest, sBlockOfTime, sDollarCap, sBillMinium,
						sQuantity, sShortHaulRule, sCostCompareOnRule,
						sWithSurcharges, sMultiRateSchedule, sAirTime,
						sVolumeDiscount, sContractDiscount, sIncrements60,
						sIncrements60or30, sIncrements30, sIncrements10,
						sIncrements1, sPeakOffPeak, sIs247, sThd1,
						provincialOntarioOnly, provincialQuebecOnly);
                  
                r++;
                stt++;
			}
        }
		return workbook;
	}


	private void setBackgroundColor(HSSFCellStyle cellStyleredColorDes, HSSFColor color) {
		cellStyleredColorDes.setFillForegroundColor(color.getIndex());
		cellStyleredColorDes.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	}

	private void setBorderLine(HSSFCellStyle bgcolor) {
		bgcolor.setBorderTop((short) 1); // single line border
		bgcolor.setBorderBottom((short) 1); // single line border
		bgcolor.setBorderLeft((short) 1); // single line border
		bgcolor.setBorderRight((short) 1); // single line border
	}

	private void setValueRow(HSSFCellStyle bgcolor, HSSFRow row,
			String pricePlanName, String prioprityCode, String usoc,
			String pricePlanCode, String lineLevel, String sAccountLevel,
			String sIsToolPricingPlan, String sNoOverseaDest,
			String sTop28OverseaDest, String sT29OverseaDest,
			String sBlockOfTime, String sDollarCap, String sBillMinium,
			String sQuantity, String sShortHaulRule, String sCostCompareOnRule,
			String sWithSurcharges, String sMultiRateSchedule, String sAirTime,
			String sVolumeDiscount, String sContractDiscount,
			String sIncrements60, String sIncrements60or30,
			String sIncrements30, String sIncrements10, String sIncrements1,
			String sPeakOffPeak, String sIs247, String sThd1,
			String provincialOntarioOnly, String provincialQuebecOnly) {
		HSSFCell cell;
		cell = row.createCell(1);
		cell.setCellValue(getStringCell(pricePlanCode + ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(2);
		cell.setCellValue(getStringCell(pricePlanName+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(3);
		cell.setCellValue(getStringCell(sAccountLevel+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(4);
		cell.setCellValue(getStringCell(lineLevel+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(5);
		cell.setCellValue(getStringCell(prioprityCode+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(6);
		cell.setCellValue(getStringCell(usoc+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(7);
		cell.setCellValue(getStringCell(sIsToolPricingPlan+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell(8);
		cell.setCellValue(getStringCell(sNoOverseaDest+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(9);
		cell.setCellValue(getStringCell(sTop28OverseaDest+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(10);
		cell.setCellValue(getStringCell(sT29OverseaDest+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(11);
		cell.setCellValue(getStringCell(sBlockOfTime+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell( 12);
		cell.setCellValue(getStringCell(sDollarCap+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell( 13);
		cell.setCellValue(getStringCell(sBillMinium+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 14);
		cell.setCellValue(getStringCell(sQuantity+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 15);
		cell.setCellValue(getStringCell(sShortHaulRule+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 16);
		cell.setCellValue(getStringCell(sCostCompareOnRule+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 17);
		cell.setCellValue(getStringCell(sWithSurcharges+ ""));
		cell.setCellStyle(bgcolor);
				
		cell = row.createCell( 18);
		cell.setCellValue(getStringCell(sMultiRateSchedule+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 19);
		cell.setCellValue(getStringCell(sAirTime+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 20);
		cell.setCellValue(getStringCell(sVolumeDiscount+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 21);
		cell.setCellValue(getStringCell(sContractDiscount+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 22);
		cell.setCellValue(getStringCell(sIncrements60+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 23);
		cell.setCellValue(getStringCell(sIncrements60or30+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 24);
		cell.setCellValue(getStringCell(sIncrements30+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 25);
		cell.setCellValue(getStringCell(sIncrements10+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 26);
		cell.setCellValue(getStringCell(sIncrements1+ ""));
		cell.setCellStyle(bgcolor);
		                    
		cell = row.createCell( 27);
		cell.setCellValue(getStringCell(sPeakOffPeak+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 28);
		cell.setCellValue(getStringCell(sIs247+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 29);
		cell.setCellValue(getStringCell(sThd1+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 30);
		cell.setCellValue(getStringCell(provincialOntarioOnly+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 31);
		cell.setCellValue(getStringCell(provincialQuebecOnly+ ""));
		cell.setCellStyle(bgcolor);
	}

	private void setRowNoteData(HSSFCellStyle cellStyleYellowColorDes, HSSFCellStyle cellStyleRedColorDes, HSSFCellStyle cellStyleBorder, HSSFSheet sheet) {
		HSSFRow row;
		HSSFCell cell;
		row = sheet.createRow( (1));
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell(""));
		cell.setCellStyle(cellStyleRedColorDes);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Consumer"));
		cell.setCellStyle(cellStyleBorder);
		
		row = sheet.createRow( (2));	
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell(""));
		cell.setCellStyle(cellStyleYellowColorDes);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Business"));
		cell.setCellStyle(cellStyleBorder);
		
		row = sheet.createRow( (3));	
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell("No"));
		cell.setCellStyle(cellStyleBorder);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Not eligible"));
		cell.setCellStyle(cellStyleBorder);
		
		row = sheet.createRow( (4));	
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell("V"));
		cell.setCellStyle(cellStyleBorder);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Eligible"));
		cell.setCellStyle(cellStyleBorder);
	}

	private void setWidthColumn(HSSFSheet sheet) {
		// Set Column Widths
		sheet.setColumnWidth(0, 800);
		sheet.setColumnWidth(1, 3800);
		sheet.setColumnWidth(2, 12000);
		sheet.setColumnWidth(3, 3300);
		sheet.setColumnWidth(4, 2400);
		sheet.setColumnWidth(5, 3700);
		sheet.setColumnWidth(6, 20000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 20000);
		sheet.setColumnWidth(9, 8000);
		sheet.setColumnWidth(10, 8000);
		sheet.setColumnWidth(11, 3200);
		sheet.setColumnWidth(12, 2500);
		sheet.setColumnWidth(13, 3200);
		sheet.setColumnWidth(14, 5000);
		sheet.setColumnWidth(15, 5000);
		sheet.setColumnWidth(16, 6000);
		sheet.setColumnWidth(17, 6000);
		sheet.setColumnWidth(18, 5500);
		sheet.setColumnWidth(19, 2000);
		sheet.setColumnWidth(20, 4000);
		sheet.setColumnWidth(21, 4000);
		sheet.setColumnWidth(22, 5000);
		sheet.setColumnWidth(23, 6000);
		sheet.setColumnWidth(24, 4000);
		sheet.setColumnWidth(25, 4000);
		sheet.setColumnWidth(26, 4000);
		sheet.setColumnWidth(27, 3500);
		sheet.setColumnWidth(28, 3500);
		sheet.setColumnWidth(29, 7000);
		sheet.setColumnWidth(30, 5000);
		sheet.setColumnWidth(31, 5000);
	}
	
	private HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor = palette.findColor(r, g, b);
			if (hssfColor == null) {
				palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
				hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return hssfColor;
	}
	
	private HSSFRichTextString getStringCell(String str){
		if(str == null || str.isEmpty()){
			return null;
		}
		HSSFRichTextString result = new HSSFRichTextString(str);
		return result;
	}
}
