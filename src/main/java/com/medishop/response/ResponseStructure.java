package com.medishop.response;

import lombok.Data;

/**
 * A generic response structure for API responses.
 *
 * @param <T> the type of the response data
 */
@Data
public class ResponseStructure<T> {

	private T data;
	private String msg;
	private int status;

	/**
	 * Static method to create a ResponseStructure.
	 *
	 * @param status the HTTP status code
	 * @param msg the message to be included in the response
	 * @param data the data to be included in the response
	 * @param <T> the type of the response data
	 * @return a new instance of ResponseStructure
	 */
	public static <T> ResponseStructure<T> createResponse(int status, String msg, T data) {
		ResponseStructure<T> response = new ResponseStructure<>();
		response.setData(data);
		response.setMsg(msg);
		response.setStatus(status);
		return response;
	}
}