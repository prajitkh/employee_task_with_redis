package com.task.weather;

import java.util.Map;

public class Response {
	private String message;
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response(String temp1, Map<String, Object> temp2) {
		this.message = temp1;
		this.data = temp2;
	}

}
