
package com.example.demo.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.demo.utility.ResponseErrorData;
import com.example.demo.utility.ResponseStatus;

public class ResponseErrorHandler extends Exception
{
	private BindingResult bindingResult;
	ResponseErrorData responseErrorData;


	public ResponseErrorHandler(BindingResult bindingResult)
	{

		super();
		this.bindingResult = bindingResult;

		List<ObjectError> errors = bindingResult.getAllErrors();
		List<String> messages = new ArrayList<>();

		for (ObjectError error : errors)
		{
			messages.add(error.getDefaultMessage());
		}
		responseErrorData = new ResponseErrorData();
		responseErrorData.setMessage(messages);
		responseErrorData.setStatus(ResponseStatus.error);
	}


	public ResponseErrorData getResponseErrorData()
	{

		return responseErrorData;
	}


	public void setResponseErrorData(ResponseErrorData responseErrorData)
	{

		this.responseErrorData = responseErrorData;
	}

}
