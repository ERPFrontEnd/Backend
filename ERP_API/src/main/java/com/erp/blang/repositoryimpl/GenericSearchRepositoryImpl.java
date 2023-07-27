package com.erp.blang.repositoryimpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.erp.blang.core.GenericSearchRepository;
import com.erp.blang.filter.Lookup;
import com.erp.blang.filter.SearchRequest;
import com.erp.blang.repositoryutils.QueryUtils;
import com.mongodb.client.result.UpdateResult;

@Repository
public class GenericSearchRepositoryImpl implements GenericSearchRepository	{

	@Autowired
	MongoTemplate template;
	
	@Override
	public List<?> search(SearchRequest request, String key, String value, Class<?> clazz) {

		Query query = new Query();
		Criteria criteria = Criteria.where(key).is(value);
		if (request != null) {

			if (request.getSearch() != null && !request.getSearch().isEmpty()) {

				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if (request.getPageSize() > 0) {

				PageRequest page = PageRequest.of(request.getCurrentPage(), request.getPageSize(),
						Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);
			}
		}

		query.addCriteria(criteria);

		return template.find(query, clazz);
	}

	
	@Override
	public List<?> searchList(SearchRequest request, int searchId, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = new Criteria();

		if (request != null &&  (request.getSearch() != null && !request.getSearch().isEmpty())) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			

		}
		query.addCriteria(criteria).addCriteria(Criteria.where("kisanSathiId").is(searchId));
		return template.find(query, clazz);
	}

	@Override
	public long searchCount(SearchRequest request, String key, String value, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = Criteria.where(key).is(value);
		if (request != null && (request.getSearch() != null && !request.getSearch().isEmpty())) {
				QueryUtils.buildQuery(criteria, request.getSearch());
		
		}
		query.addCriteria(criteria);

		return template.count(query, clazz);
	}

	@Override
	public List<?> search(SearchRequest request, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = new Criteria();

		if (request != null) {
			if (request.getSearch() != null && !request.getSearch().isEmpty()) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			}

			if (request.getPageSize() > 0) {
				PageRequest page = PageRequest.of(request.getCurrentPage(), request.getPageSize(),
						Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);
			}
		}
		query.addCriteria(criteria);

		return template.find(query, clazz);
	}

	@Override
	public long searchCount(SearchRequest request, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if (request != null &&  (request.getSearch() != null && !request.getSearch().isEmpty())) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			
		}
		query.addCriteria(criteria);
		return template.count(query, clazz);
	}

	@Override
	public List<?> searchne(SearchRequest request, String key, List<String> value, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = Criteria.where(key).nin(value);
		if (request != null) {

			if (request.getSearch() != null && !request.getSearch().isEmpty()) {

				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if (request.getPageSize() > 0) {

				PageRequest page = PageRequest.of(request.getCurrentPage(), request.getPageSize(),
						Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);
			}
		}

		query.addCriteria(criteria);

		return template.find(query, clazz);
	}

	@Override
	public List<?> searchIn(SearchRequest request, String key, List<String> value, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = Criteria.where(key).in(value);
		if (request != null) {

			if (request.getSearch() != null && !request.getSearch().isEmpty()) {

				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if (request.getPageSize() > 0) {

				PageRequest page = PageRequest.of(request.getCurrentPage(), request.getPageSize(),
						Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);
			}
		}

		query.addCriteria(criteria);

		return template.find(query, clazz);
	}

	@Override
	public long searchCountIn(SearchRequest request, String key, List<String> value, Class<?> clazz) {
		Query query = new Query();
		Criteria criteria = Criteria.where(key).in(value);
		if (request != null) {
			if (request.getSearch() != null && !request.getSearch().isEmpty()) {
				QueryUtils.buildQuery(criteria, request.getSearch());
			}
			if (request.getPageSize() > 0) {
				PageRequest page = PageRequest.of(request.getCurrentPage(), request.getPageSize(),
						Direction.fromString(request.getSort()), request.getSortBy());
				query.with(page);
			}
		}
		query.addCriteria(criteria);

		return template.count(query, clazz);
	}

	@Override
	public List<?> in(List<String> ids, String key, Class<?> clazz) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).in(ids));
		return template.find(query, clazz);
	}

	@Override
	public List<?> lookup(Lookup lookup) {
		LookupOperation lookupOperation = LookupOperation.newLookup().from(lookup.getFrom())
				.localField(lookup.getLocalField()).foreignField(lookup.getForeignField()).as(lookup.getAs());

		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where(lookup.getSearchKey()).in(lookup.getSearchValues())), lookupOperation);
		return template.aggregate(aggregation, lookup.getInputTypeClazz(), lookup.getOutputTypeClazz())
				.getMappedResults();
	}

	@Override
	public List<?> searchListToCsv(SearchRequest request, Class<?> clazz) {
		return Collections.emptyList();
	}

	@SuppressWarnings("static-access")
	@Override
	public UpdateResult update(String searchKey, List<String> searchValue, String replaceKey, String replaceValue,
			Class<?> clazz) {
		UpdateResult result = template.updateMulti(new Query(Criteria.where(searchKey).in(searchValue)),
				new Update().set(replaceKey, replaceValue), clazz);
		return result.unacknowledged();
	}
}
