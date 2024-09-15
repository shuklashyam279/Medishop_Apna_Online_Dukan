package com.medishop.response;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Shyam Shukla
 * @param <T>
 */
@Component
@Data
public class ResponseStructure<T> {

	private T data;
	private String msg;
	private int status;

}