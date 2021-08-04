package com.meli.ipvalidator.dto;

import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto<T> {
	private HttpStatus responseCode;
	private String message;
	private Object data;
	private String transactionId;

	public ResponseDto(HttpStatus responseCode, String message, DataDto data, String transactionId) {
		super();
		this.responseCode = responseCode;
		this.message = message;
		this.data = data;
		this.transactionId = transactionId;
	}

}
