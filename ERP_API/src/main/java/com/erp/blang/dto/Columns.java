package com.erp.blang.dto;

public class Columns {
	private String columnName;
	private String displayName;
	private Boolean isSearchable;
	private String dataType;
	private String colWidth;
	private Boolean isVisible = true;
	
	public Columns(String columnName, String displayName, boolean isSearchable) {
		super();
		this.columnName = columnName;
		this.displayName = displayName;
		this.isSearchable = isSearchable;
	}
	public Columns(String columnName, String displayName, boolean isSearchable, String dataType, String colWidth) {
		super();
		this.columnName = columnName;
		this.displayName = displayName;
		this.isSearchable = isSearchable;
		this.dataType = dataType;
		this.colWidth = colWidth;
	}
	public Columns(String columnName, String displayName, boolean isSearchable, boolean isVisible, String dataType, String colWidth) {
		super();
		this.columnName = columnName;
		this.displayName = displayName;
		this.isSearchable = isSearchable;
		this.isVisible = isVisible;
		this.dataType = dataType;
		this.colWidth = colWidth;
	}

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isSearchable() {
		return isSearchable;
	}
	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColWidth() {
		return colWidth;
	}

	public void setColWidth(String colWidth) {
		this.colWidth = colWidth;
	}
	public Boolean getIsSearchable() {
		return isSearchable;
	}
	public void setIsSearchable(Boolean isSearchable) {
		this.isSearchable = isSearchable;
	}
	public Boolean getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

}
