package com.task.dto;

public class EmployeePaginationResponse {

	private int pageSize;
	private int pageNo;
	private Long total;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public EmployeePaginationResponse(int pageSize, int pageNo, Long total) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.total = total;
	}

	public EmployeePaginationResponse() {
		super();

	}

}
