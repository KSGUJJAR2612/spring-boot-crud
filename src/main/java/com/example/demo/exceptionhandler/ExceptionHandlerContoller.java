
package com.example.demo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.utility.ResponseData;
import com.example.demo.utility.ResponseErrorData;
import com.example.demo.utility.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerContoller
{

	@ExceptionHandler(ResourceNotFoundExceptioin.class)
	public ResponseEntity<ResponseData> resourceNotFoundException(ResourceNotFoundExceptioin exception)
	{
		ResponseData responseData = new ResponseData();
		responseData.setStatus(exception.getStatus());
		responseData.setMessage(exception.getMessage());
		return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseData> argumentMismatchException(MethodArgumentTypeMismatchException exception)
	{

		ResponseData responseData = new ResponseData();
		responseData.setStatus(ResponseStatus.error);
		responseData.setMessage(ResponseStatus.invalidArgumentMessage);

		return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseData> nullpointerException(NullPointerException exception)
	{

		ResponseData responseData = new ResponseData();
		responseData.setStatus(ResponseStatus.nullPointer);
		responseData.setMessage(ResponseStatus.nullPointerMessage);

		return new ResponseEntity<ResponseData>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(ResponseErrorHandler.class)
	public ResponseEntity<ResponseErrorData> validationError(ResponseErrorHandler responseErrorHandler)
	{

		return new ResponseEntity<ResponseErrorData>(responseErrorHandler.getResponseErrorData(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
