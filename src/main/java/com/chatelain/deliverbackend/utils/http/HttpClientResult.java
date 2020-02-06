package com.chatelain.deliverbackend.utils.http;

import lombok.Data;

@Data
public class HttpClientResult {

	private int code;

	private String content;

	public HttpClientResult() {
	}

	public HttpClientResult(int code) {
		this.code = code;
	}

	public HttpClientResult(String content) {
		this.content = content;
	}

	public HttpClientResult(int code, String content) {
		this.code = code;
		this.content = content;
	}

}
