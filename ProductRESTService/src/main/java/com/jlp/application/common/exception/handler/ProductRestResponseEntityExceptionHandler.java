package com.jlp.application.common.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jlp.application.common.exception.FetchResultException;
import com.jlp.application.common.exception.RecordProcessException;
import com.jlp.application.model.ProductServiceError;
/**
 * @author Manoj
 *
 *Global Exception handler for Product REST services
 */
@ControllerAdvice
public class ProductRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value 
		      = { FetchResultException.class, RecordProcessException.class })
		    protected ResponseEntity<Object> handleConflict(
		      RuntimeException ex, WebRequest request) {
		        ProductServiceError error= new ProductServiceError();
		        error.setErrorString(ex.getMessage());
		        return handleExceptionInternal(ex, error, 
		          new HttpHeaders(), HttpStatus.CONFLICT, request);
		    }

}
