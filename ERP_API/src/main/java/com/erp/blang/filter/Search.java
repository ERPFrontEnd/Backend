package com.erp.blang.filter;

public class Search {

	String key;
	Object value;
	String operation;

	public Search() {
		
	}

	public Search(String key, Object value, String operation) {
		super();
		this.key = key;
		this.value = value;
		this.operation = operation;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Search [key=" + key + ", value=" + value + ", operation=" + operation + "]";
	}
}
