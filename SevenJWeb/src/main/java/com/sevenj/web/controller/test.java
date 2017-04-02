package com.sevenj.web.controller;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		ToolPlanService toolPlanService = new ToolPlanServiceImpl();
//		List<ToolPlan> toolPlans = toolPlanService.getToolPlanList("ADOP",1);
//		System.out.println(toolPlans.size());
//		for(ToolPlan tool:toolPlans){
//			System.out.println(tool.getTerminatingDestinationCode());
//		}
		FileOutputStream fileOut = new FileOutputStream("poi-test.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
		HSSFDataFormat format = workbook.createDataFormat();
		// index from 0,0... cell A1 is cell(0,0)
		HSSFRow row1 = worksheet.createRow(0);

		HSSFCell cellA1 = row1.createCell(0);
		cellA1.setCellValue(0.21);
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(format.getFormat("00.0000"));
		cellA1.setCellStyle(cellStyle);

		HSSFRow row2 = worksheet.createRow(1);
		HSSFCell cellA2 = row2.createCell(0);
		cellA2.setCellValue(12736);
		
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

}
