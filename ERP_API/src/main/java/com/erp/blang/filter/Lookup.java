package com.erp.blang.filter;

import java.util.List;

public class Lookup {
	
	private String from;
	private String localField;
	private String foreignField;
	private String as;
	private String searchKey;
	private List<?> searchValues;
	private Class<?> inputTypeClazz;
	private Class<?> outputTypeClazz;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getLocalField() {
		return localField;
	}
	public void setLocalField(String localField) {
		this.localField = localField;
	}
	public String getForeignField() {
		return foreignField;
	}
	public void setForeignField(String foreignField) {
		this.foreignField = foreignField;
	}
	public String getAs() {
		return as;
	}
	public void setAs(String as) {
		this.as = as;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public List<?> getSearchValues() {
		return searchValues;
	}
	public void setSearchValues(List<?> searchValues) {
		this.searchValues = searchValues;
	}
	public Class<?> getInputTypeClazz() {
		return inputTypeClazz;
	}
	public void setInputTypeClazz(Class<?> inputTypeClazz) {
		this.inputTypeClazz = inputTypeClazz;
	}
	public Class<?> getOutputTypeClazz() {
		return outputTypeClazz;
	}
	public void setOutputTypeClazz(Class<?> outputTypeClazz) {
		this.outputTypeClazz = outputTypeClazz;
	}
}
