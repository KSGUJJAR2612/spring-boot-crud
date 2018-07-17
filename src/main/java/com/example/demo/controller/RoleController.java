
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commandobject.RoleCommand;
import com.example.demo.service.RoleService;
import com.example.demo.utility.ResponseData;
import com.example.demo.utility.ResponseStatus;

@RestController
@RequestMapping("/api/v1")
public class RoleController
{
	@Autowired
	private RoleService roleService;


	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseData save(@RequestBody RoleCommand role)
	{

		ResponseData responseData = new ResponseData();
		if (roleService.save(role))
		{
			responseData.setStatus(ResponseStatus.success);
			responseData.setMessage(ResponseStatus.successMessage);
			responseData.setData(role);
		}
		else
		{
			responseData.setStatus(ResponseStatus.fail);
			responseData.setMessage(ResponseStatus.failMessage);
		}

		return responseData;
	}

}
