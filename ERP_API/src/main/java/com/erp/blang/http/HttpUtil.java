package com.erp.blang.http;

/**
 * @author Silxo
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import org.springframework.stereotype.Component;

@Component
public class HttpUtil {

	public HttpResponse post(HttpRequest request) throws IOException {

		CloseableHttpResponse httpResponse;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(request.getUrl());

			if(request.getHeaders() != null) {
				for (Map.Entry<String, Object> header : request.getHeaders().entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue().toString());
				}
			}

			if(request.getParams() != null) {
				List<NameValuePair> urlParameters = new ArrayList<>();

				for (Map.Entry<String, Object> param : request.getParams().entrySet()) {
					urlParameters.add(new BasicNameValuePair(param.getKey(), param.getValue().toString()));
				}

				org.apache.http.HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
				httpPost.setEntity(postParams);
			} else if(request.getData() != null) {
				httpPost.setEntity(new StringEntity(request.getData()));
			}

			httpResponse = httpClient.execute(httpPost);
			return parseResponse(httpResponse);
		}
	}

	public HttpResponse get(HttpRequest request) throws IOException {

		CloseableHttpResponse httpResponse;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet(request.getUrl());
			if(request.getHeaders() != null) {
				for (Map.Entry<String, Object> header : request.getHeaders().entrySet()) {
					httpGet.addHeader(header.getKey(), header.getValue().toString());
				}
			}

			httpResponse = httpClient.execute(httpGet);

			return parseResponse(httpResponse);		
		}
	}

	public HttpResponse put(HttpRequest request) throws IOException {

		CloseableHttpResponse httpResponse;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPut httpPost = new HttpPut(request.getUrl());
			if(request.getHeaders() != null) {
				for (Map.Entry<String, Object> header : request.getHeaders().entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue().toString());
				}
			}

			if(request.getParams() != null) {
				List<NameValuePair> urlParameters = new ArrayList<>();

				for (Map.Entry<String, Object> param : request.getParams().entrySet()) {
					urlParameters.add(new BasicNameValuePair(param.getKey(), param.getValue().toString()));
				}

				HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
				httpPost.setEntity(postParams);
			} else if(request.getData() != null){
				httpPost.setEntity(new StringEntity(request.getData()));
			}

			httpResponse = httpClient.execute(httpPost);

			return parseResponse(httpResponse);
		}
	}

	public HttpResponse parseResponse(CloseableHttpResponse httpResponse) throws UnsupportedOperationException, IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String inputLine;
		StringBuilder data = new StringBuilder();

		while ((inputLine = reader.readLine()) != null) {
			data.append(inputLine);
		}
		reader.close();

		HttpResponse response =  new HttpResponse(httpResponse.getStatusLine().getStatusCode(), data.toString());
		return response;
	}



	public HttpResponse delete(HttpRequest request) throws UnsupportedOperationException, IOException {

		CloseableHttpResponse httpResponse;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpDelete httpPost = new HttpDelete(request.getUrl());
			if(request.getHeaders() != null) {
				for (Map.Entry<String, Object> header : request.getHeaders().entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue().toString());
				}
			}

			if(request.getParams() != null) {
				List<NameValuePair> urlParameters = new ArrayList<>();

				for (Map.Entry<String, Object> param : request.getParams().entrySet()) {
					urlParameters.add(new BasicNameValuePair(param.getKey(), param.getValue().toString()));
				}

				HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
				((org.apache.http.HttpResponse) httpPost).setEntity(postParams);
			} else if(request.getData() != null){
				((org.apache.http.HttpResponse) httpPost).setEntity(new StringEntity(request.getData()));
			}

			httpResponse = httpClient.execute(httpPost);

			return parseResponse(httpResponse);
		}
	}
}