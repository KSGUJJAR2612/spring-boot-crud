
package com.example.demo.utility;

import java.util.List;

public class ResponseErrorData
{
	private String status;
	private List<String> message;
	private Object data;


	public String getStatus()
	{

		return status;
	}


	public void setStatus(String status)
	{

		this.status = status;
	}


	public List<String> getMessage()
	{

		return message;
	}


	public void setMessage(List<String> message)
	{

		this.message = message;
	}


	public Object getData()
	{

		return data;
	}


	public void setData(Object data)
	{

		this.data = data;
	}

}
