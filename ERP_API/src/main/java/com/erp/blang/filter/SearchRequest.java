package com.erp.blang.filter;

import java.util.List;

public class SearchRequest {

	private int currentPage;
	private List<Search> search;
	private String sortBy;
	private String sort;
	private int pageSize;
	private String areaManagerId;

	public String getAreaManagerId() {
		return areaManagerId;
	}

	public void setAreaManagerId(String areaManagerId) {
		this.areaManagerId = areaManagerId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Search> getSearch() {
		return search;
	}

	public void setSearch(List<Search> search) {
		this.search = search;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "SearchRequest [currentPage=" + currentPage + ", search=" + search + ", sortBy=" + sortBy + ", sort="
				+ sort + ", pageSize=" + pageSize + "]";
	}

}
