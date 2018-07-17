
package com.example.demo.exceptionhandler;

public class ResourceNotFoundExceptioin extends RuntimeException
{
	private String status;
	private String message;


	public ResourceNotFoundExceptioin(String status, String message)
	{

		super();
		this.status = status;
		this.message = message;
	}


	public String getStatus()
	{

		return status;
	}


	public void setStatus(String status)
	{

		this.status = status;
	}


	@Override
	public String getMessage()
	{

		return message;
	}


	public void setMessage(String message)
	{

		this.message = message;
	}

}
