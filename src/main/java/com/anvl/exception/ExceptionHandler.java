package com.anvl.exception;

import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.exceptions.TemplateProcessingException;

@ControllerAdvice
public class ExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
	public String resourceNotFound(Exception ex, WebRequest request) {
		return "error/404";
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
	public String internalServerError(Exception ex, WebRequest request) {
		return "error/500";
	}

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
	public String unauthorized(Exception ex, WebRequest request) {
		return "error/401";
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { MethodArgumentTypeMismatchException.class,
			SpelEvaluationException.class, TemplateInputException.class, TemplateProcessingException.class })
	public String internalServerError2(Exception ex, WebRequest request) {
		return "error/500";
	}

}
