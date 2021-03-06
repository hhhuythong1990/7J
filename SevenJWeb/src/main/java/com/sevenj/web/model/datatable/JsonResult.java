package com.sevenj.web.model.datatable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sevenj.model.Project;

public class JsonResult<T> extends JsonResultBase{
	private T record;
	public T getRecord() {
		return record;
	}
	
	@JsonProperty("Record")
	public void setRecord(T record) {
		this.record = record;
	}

	public JsonResult(){}	
}
