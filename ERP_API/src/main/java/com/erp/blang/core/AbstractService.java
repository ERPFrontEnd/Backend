package com.erp.blang.core;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.erp.blang.filter.SearchRequest;

public interface AbstractService<T> {

	public ResponseEntity<Object> save(T bean);

	public ResponseEntity<Object> save(List<T> beans);

	public ResponseEntity<Object> update(T bean);
	
	public ResponseEntity<Object> delete(String id);

	long getCount();

	public long searchCount(SearchRequest request);


}
