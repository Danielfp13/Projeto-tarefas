package com.api.tarefas.resource.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> list = new ArrayList<>();
	
	public ValidationError(Integer status, String message, String error, LocalDateTime timestamp, String path) {
		super(status, message, error, timestamp, path);
		
	}

	public List<FieldMessage> getErros() {
		return list;
	}

	public void addError(String fieldName, String massage) {
		this.list.add(new FieldMessage(fieldName, massage));
	}

}
