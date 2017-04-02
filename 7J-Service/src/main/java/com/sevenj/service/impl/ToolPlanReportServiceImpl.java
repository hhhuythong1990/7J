package com.sevenj.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.sevenj.dal.dao.ToolPlanDAO;
import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.ToolPlan;
import com.sevenj.service.IReportService;

public class ToolPlanReportServiceImpl implements IReportService<ToolPlan>{

	private Logger logger = Logger.getLogger(ToolPlanReportServiceImpl.class.getName());
	ToolPlanDAO dao;
	private SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
//	ServiceUtils serviceUtil = new ServiceUtils();
	
	@Override
	public HSSFWorkbook exportExcel(List<ToolPlan> list) throws Exception {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyleTitle, cellStyleRecordYellow, cellStyleRecordRed, cellStyleYellowColorDes, cellStyleredColorDes, cellStyleBorder, cellStyleBackgroundColor = null;
		HSSFFont fontTitle, fontRowContent;
		HSSFSheet sheet = workbook.createSheet("Tool Price Plan Info");
		
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
		HSSFRow row;
		HSSFCell cell;
		int stt = 1;
		int r = 1;
		
        cellStyleTitle = workbook.createCellStyle();
		HSSFColor colorTitle =  setColor(workbook,(byte) 192, (byte)192,(byte) 192);
		setBackgroundColor(cellStyleTitle, colorTitle);
		cellStyleTitle.setFont(fontTitle);
		cellStyleTitle.setAlignment((short) 0);
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitle.setWrapText(true);   //Wrapping text
		setBorderLine(cellStyleTitle);
		
		
//		cellStyleYellowColorDes = workbook.createCellStyle();
//		HSSFColor yellow =  setColor(workbook,(byte) 255, (byte)255,(byte) 0);
//		setBackgroundColor(cellStyleYellowColorDes, yellow);
//		
//		cellStyleredColorDes = workbook.createCellStyle();
//		HSSFColor Red =  setColor(workbook,(byte) 255, (byte)102,(byte) 0);
//		setBackgroundColor(cellStyleredColorDes, Red);
		
		setWidthColumn(sheet);
		// Create Title Excel
		String[] rowTitle = {"Updates to be done by operations","Pricing plan code","Week definition code","Rate period sequence number",
				"Type of call","Sqecial call type code","Originating destination code","Originating satellite","Terminating destination code",
				"Terminating satellite code","Race CAP sequence number","Customer specific rating code","Table entry effective date","Rate Type",
				"Rate","Rate time unit","Message lecel percent discount","CAP threshld code","Minimum Time","Increment time","Increament time unit",
				"Rate Validation indicator","Table entry expriration date","Table entry creation date"};
  	  	String[] rowSubTitle ={"Pic x(6)","Pic x(4)","Pic x(5)","Pic 9(4)","Pic x(1)","Pic x(4)",
  	  			"Pic x(3)","Pic x(1)","Pic x(3)","Pic x(8)","Pic 9(2)","Pic x(8)","Pic x(8)",
  	  			"Pic x(1)","Pic 9(2) V9(4)","Pic x(1)","Pic 9(3)V99","Pic x(5)","Pic 9(4)",
  	  			"Pic 9(4)","Pic x(1)","Pic x(1)","Pic x(8)","Pic x(8)"};

		row = sheet.createRow((short) 0);
		row.setHeight((short) 1350);
		cell = row.createCell(0);
		cell.setCellValue(getStringCell(""));
		
		for(int i=0;i<rowTitle.length;i++){
		  cell = row.createCell(i+1);
		  cell.setCellValue(getStringCell(rowTitle[i]));
		  cell.setCellStyle(cellStyleTitle);
	  	}
		row = sheet.createRow((short) 1);
		for(int i=0;i<rowSubTitle.length;i++){
		  cell = row.createCell(i+1);
		  cell.setCellValue(getStringCell(rowSubTitle[i]));
		  cell.setCellStyle(cellStyleTitle);
	  	}
		
		HSSFDataFormat formatNumber = workbook.createDataFormat();
		HSSFCellStyle style0000=workbook.createCellStyle();
	  	style0000.setDataFormat(formatNumber.getFormat("0000"));
	  	HSSFCellStyle style00=workbook.createCellStyle();
		style00.setDataFormat(formatNumber.getFormat("00"));
		HSSFCellStyle style000000=workbook.createCellStyle();
		style000000.setDataFormat(formatNumber.getFormat("00.0000"));
		HSSFCellStyle style00000=workbook.createCellStyle();
		style00000.setDataFormat(formatNumber.getFormat("000.00"));
		
		if(list != null && list.size() >0){
        	for (ToolPlan obj : list) {
        		long ratePeriodSequenceNumber,raceCAPSequenceNumber,rate,messageLevelPercentDiscount,MinimumTime,
        				incrementTime;
        		String updateTBDBO = "", pricingPlanCode = "", weekDefinitionCode = "",  typeOfCall = "", 
        				speacialCallTypeCode = "", originatingDetinationCode = "", originatingSatellite = "", terminatingDestinationCode = "", terminatingSatelliteCode = "",
        				 customerSpecificRatingCode="", tableEntryEffectiveDate="", rateType = "", rateTimeUnit="", 
        				 cAPThreshldCode="", increamentTimeUnit="", rateValidationIndicator="", tableEntryExpirationDate="", tableEntryCreationDate="";
        		pricingPlanCode = obj.getPricePlan().getPricePlanCode();
        		weekDefinitionCode = obj.getWeekDefinitionCode();
        		ratePeriodSequenceNumber = obj.getRatePeriodSequenceNumber();
        		typeOfCall = obj.getTypeOfCall();
        		speacialCallTypeCode = obj.getSpeacialTypeOfCall();
        		originatingDetinationCode = obj.getOriginatingDestinationCode();
        		originatingSatellite = obj.getOriginatingSatelliteCode();
        		terminatingDestinationCode = obj.getTerminatingDestinationCode();
        		terminatingSatelliteCode = obj.getTerminatingSatelliteCode();
        		raceCAPSequenceNumber = obj.getRateCapSequenceNumber();
        		customerSpecificRatingCode = obj.getCustomerSpecificRatingCode();
        		tableEntryEffectiveDate = formatDate.format(obj.getTableEntryEffectiveDate());
        		rateType = obj.getRateType();
        		rate = (long) obj.getRate();
        		rateTimeUnit = obj.getRateTimeUnit();
        		messageLevelPercentDiscount = (long) obj.getMessageLevelPercentDiscount();
        		cAPThreshldCode = obj.getCapThresholdCode();
        		MinimumTime = obj.getMinimumTime();
        		incrementTime = obj.getIncrementTime();
        		increamentTimeUnit = obj.getIncrementTimeUnit();
        		rateValidationIndicator = obj.getRateValidationIndicator();
        		tableEntryExpirationDate = formatDate.format(obj.getTableEntryExpirationDate());
        		tableEntryCreationDate = formatDate.format(obj.getTableEntryCreationDate());
        		
        		row = sheet.createRow((r + 1));
                cell = row.createCell(0);
                cell.setCellValue(stt);
        		
                setValueRow(workbook, row,updateTBDBO, pricingPlanCode,weekDefinitionCode, ratePeriodSequenceNumber,typeOfCall, speacialCallTypeCode, 
                		originatingDetinationCode,originatingSatellite, terminatingDestinationCode, terminatingSatelliteCode,
                		raceCAPSequenceNumber, customerSpecificRatingCode, tableEntryEffectiveDate, rateType,rate, rateTimeUnit,
                		messageLevelPercentDiscount,cAPThreshldCode, MinimumTime, incrementTime,increamentTimeUnit,rateValidationIndicator, 
                		tableEntryExpirationDate,tableEntryCreationDate,style0000,style00,style000000,style00000);
                  
                r++;
                stt++;
			}
        }
		return workbook;
	}

	private void setBorderLine(HSSFCellStyle bgcolor) {
		bgcolor.setBorderTop((short) 1); // single line border
		bgcolor.setBorderBottom((short) 1); // single line border
		bgcolor.setBorderLeft((short) 1); // single line border
		bgcolor.setBorderRight((short) 1); // single line border
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
	
	private void setBackgroundColor(HSSFCellStyle cellStyleredColorDes, HSSFColor color) {
		cellStyleredColorDes.setFillForegroundColor(color.getIndex());
		cellStyleredColorDes.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	}
	
	private void setWidthColumn(HSSFSheet sheet) {
		// Set Column Widths
		sheet.setColumnWidth(0, 800);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 2000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7, 2000);
		sheet.setColumnWidth(8, 2000);
		sheet.setColumnWidth(9, 2000);
		sheet.setColumnWidth(10, 2000);
		sheet.setColumnWidth(11, 2000);
		sheet.setColumnWidth(12, 2000);
		sheet.setColumnWidth(13, 2500);
		sheet.setColumnWidth(14, 2000);
		sheet.setColumnWidth(15, 2000);
		sheet.setColumnWidth(16, 2000);
		sheet.setColumnWidth(17, 2000);
		sheet.setColumnWidth(18, 2000);
		sheet.setColumnWidth(19, 2000);
		sheet.setColumnWidth(20, 2000);
		sheet.setColumnWidth(21, 2000);
		sheet.setColumnWidth(22, 2000);
		sheet.setColumnWidth(23, 2500);
		sheet.setColumnWidth(24, 2500);
	}
	private void setValueRow(HSSFWorkbook workbook,HSSFRow row,String updateTBDBO,String pricingPlanCode, String weekDefinitionCode, long ratePeriodSequenceNumber,
			String typeOfCall, String speacialCallTypeCode, String originatingDetinationCode,String originatingSatellite, String terminatingDestinationCode,
			String terminatingSatelliteCode, long raceCAPSequenceNumber,String customerSpecificRatingCode, String tableEntryEffectiveDate, String rateType,
			long rate, String rateTimeUnit, long messageLevelPercentDiscount,String cAPThreshldCode, long MinimumTime, long incrementTime,
			String increamentTimeUnit, String rateValidationIndicator,String tableEntryExpirationDate, String tableEntryCreationDate,
			HSSFCellStyle style0000,HSSFCellStyle style00,HSSFCellStyle style000000,HSSFCellStyle style00000) {
		HSSFCell cell;
		
		cell = row.createCell(1);
		cell.setCellValue(getStringCell(updateTBDBO+""));
		
		cell = row.createCell(2);
		cell.setCellValue(getStringCell(pricingPlanCode+ ""));
		
		cell = row.createCell(3);
		cell.setCellValue(getStringCell(weekDefinitionCode+ ""));
		
		cell = row.createCell(4);
		cell.setCellValue(ratePeriodSequenceNumber);
		cell.setCellStyle(style0000);
		
		cell = row.createCell(5);
		cell.setCellValue(getStringCell(typeOfCall+ ""));
		
		cell = row.createCell(6);
		cell.setCellValue(getStringCell(speacialCallTypeCode+ ""));
		
		cell = row.createCell(7);
		cell.setCellValue(getStringCell(originatingDetinationCode+ ""));
		
		cell = row.createCell(8);
		cell.setCellValue(getStringCell(originatingSatellite+ ""));
		
		cell = row.createCell(9);
		cell.setCellValue(getStringCell(terminatingDestinationCode+ ""));
		
		cell = row.createCell(10);
		cell.setCellValue(getStringCell(terminatingSatelliteCode+ ""));
		
		cell = row.createCell(11);
		cell.setCellValue(raceCAPSequenceNumber);
		cell.setCellStyle(style00);
		
		cell = row.createCell( 12);
		cell.setCellValue(getStringCell(customerSpecificRatingCode+ ""));
		
		cell = row.createCell( 13);
		cell.setCellValue(getStringCell(tableEntryEffectiveDate+ ""));
		
		cell = row.createCell( 14);
		cell.setCellValue(getStringCell(rateType+ ""));
		
		cell = row.createCell( 15);
		cell.setCellValue(rate);
		cell.setCellStyle(style000000);
		
		cell = row.createCell( 16);
		cell.setCellValue(getStringCell(rateTimeUnit+ ""));
		
		cell = row.createCell( 17);
		cell.setCellValue(messageLevelPercentDiscount);
		cell.setCellStyle(style00000);
			
		cell = row.createCell( 18);
		cell.setCellValue(getStringCell(cAPThreshldCode+ ""));
		
		cell = row.createCell( 19);
		cell.setCellValue(MinimumTime);
		cell.setCellStyle(style0000);
		
		cell = row.createCell( 20);
		cell.setCellValue(incrementTime);
		cell.setCellStyle(style0000);
		
		cell = row.createCell( 21);
		cell.setCellValue(getStringCell(increamentTimeUnit+ ""));
		
		cell = row.createCell( 22);
		cell.setCellValue(getStringCell(rateValidationIndicator+ ""));
		
		cell = row.createCell( 23);
		cell.setCellValue(getStringCell(tableEntryExpirationDate+ ""));
		
		cell = row.createCell( 24);
		cell.setCellValue(getStringCell(tableEntryCreationDate+ ""));		
	}
	private HSSFRichTextString getStringCell(String str){
		if(str == null || str.isEmpty()){
			return null;
		}
		HSSFRichTextString result = new HSSFRichTextString(str);
		return result;
	}
}
