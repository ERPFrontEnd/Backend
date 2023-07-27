package com.erp.blang.responsehandle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object responseObject) {

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("httpStatus", httpStatus);
		response.put("status", responseObject);

		return new ResponseEntity<>(response, httpStatus);
	}

	public static ResponseEntity<Object> responseBuilder(HttpStatus httpStatus, Object responseObject) {

		Map<String, Object> response = new HashMap<>();
		response.put("httpStatus", httpStatus);
		response.put("data", responseObject);

		return new ResponseEntity<>(response, httpStatus);
	}

	public static ResponseEntity<Object> responseBuilder(Object responseObject) {

		Map<String, Object> response = new HashMap<>();
		response.put("data", responseObject);

		return ResponseEntity.ok(response);
	}
}
