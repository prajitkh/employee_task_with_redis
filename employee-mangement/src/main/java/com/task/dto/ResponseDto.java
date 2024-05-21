package com.task.dto;

public class ResponseDto {

	private Object data;
	private EmployeePaginationResponse paginationResponse;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public EmployeePaginationResponse getPaginationResponse() {
		return paginationResponse;
	}

	public void setPaginationResponse(EmployeePaginationResponse paginationResponse) {
		this.paginationResponse = paginationResponse;
	}

}
