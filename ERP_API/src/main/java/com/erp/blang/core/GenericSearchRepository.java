package com.erp.blang.core;

import java.util.List;

import com.erp.blang.filter.Lookup;
import com.erp.blang.filter.SearchRequest;
import com.mongodb.client.result.UpdateResult;


public interface GenericSearchRepository {

	public List<?> searchList(SearchRequest request, int searchId, Class<?> clazz);

	public List<?> search(SearchRequest request, String key, String value, Class<?> clazz);
	
	public long searchCount(SearchRequest request, String key, String value, Class<?> clazz);

	public List<?> search(SearchRequest request, Class<?> clazz);

	public long searchCount(SearchRequest request, Class<?> clazz);

	public List<?> searchne(SearchRequest request, String key, List<String> list, Class<?> clazz);

	public List<?> searchIn(SearchRequest request, String key, List<String> value, Class<?> clazz);

	public long searchCountIn(SearchRequest request, String key, List<String> value, Class<?> clazz);

	public List<?> in(List<String> ids, String key, Class<?> clazz);

	public List<?> lookup(Lookup lookup);

	public List<?> searchListToCsv(SearchRequest request, Class<?> clazz);

	public UpdateResult update(String searchKey, List<String> searchValue, String replaceKey, String replaceValue,
			Class<?> clazz);

}
