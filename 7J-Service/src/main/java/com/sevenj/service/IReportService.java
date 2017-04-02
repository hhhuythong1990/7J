package com.sevenj.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface IReportService<T> {

	public HSSFWorkbook exportExcel(List<T> data) throws Exception;
}
