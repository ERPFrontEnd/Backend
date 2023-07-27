package com.erp.blang.http;

/**
 * @author Silxo
 *
 */

import java.util.Map;

public class HttpRequest {

	private String url;
	private String data;
	private Map<String, Object> headers;
	private Map<String, Object> params;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Map<String, Object> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "URL :: "+url+"  Data :: "+data+"  Headers ::"+headers+"   Params :: "+params;
	}
}