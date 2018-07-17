
package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commandobject.UserCommand;
import com.example.demo.exceptionhandler.ResourceNotFoundExceptioin;
import com.example.demo.exceptionhandler.ResponseErrorHandler;
import com.example.demo.service.UserService;
import com.example.demo.utility.ResponseData;
import com.example.demo.utility.ResponseStatus;

@RestController
@RequestMapping("/api/v1")
public class User_Controller {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseData save(@Valid @RequestBody UserCommand user, BindingResult bindingResult) throws Exception {
		ResponseData responseData = new ResponseData();
		if (bindingResult.hasErrors()) {

			System.out.println("VAlidation Error===>>>>");
			throw new ResponseErrorHandler(bindingResult);
		}

		if (userService.save(user)) {
			responseData.setStatus(ResponseStatus.success);
			responseData.setMessage(ResponseStatus.successMessage);
			responseData.setData(user);
		} else {
			responseData.setStatus(ResponseStatus.fail);
			responseData.setMessage(ResponseStatus.failMessage);
		}

		return responseData;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public ResponseData getUserById(@PathVariable Long userId) {

		ResponseData responseData = new ResponseData();

		if (userService.getUserById(userId) != null) {
			responseData.setStatus(ResponseStatus.success);
			responseData.setMessage(ResponseStatus.successMessage);
			responseData.setData(userService.getUserById(userId));
		} else {
			throw new ResourceNotFoundExceptioin(ResponseStatus.notFound, ResponseStatus.notFoundMessage);
		}

		return responseData;
	}

	@RequestMapping(value = "/roles/{roleName}", method = RequestMethod.GET)
	public ResponseData getUserByRole(@PathVariable String roleName) {

		ResponseData responseData = new ResponseData();

		if (userService.getUserByRole(roleName) != null) {
			responseData.setStatus(ResponseStatus.success);
			responseData.setMessage(ResponseStatus.successMessage);
			responseData.setData(userService.getUserByRole(roleName));
		}
		return responseData;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseData getUser(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {

		ResponseData responseData = new ResponseData();
		if (page != null && size != null) {
			Pageable pageable = new PageRequest(page, size);
			List<UserCommand> users = userService.getUserByOffset(pageable);

			if (users.isEmpty()) {
				responseData.setStatus(ResponseStatus.notFound);
				responseData.setMessage(ResponseStatus.notFoundMessage);
			} else {
				responseData.setStatus(ResponseStatus.success);
				responseData.setMessage(ResponseStatus.successMessage);
				responseData.setData(users);
			}
		} else {
			if (userService.getAll() != null) {
				responseData.setStatus(ResponseStatus.success);
				responseData.setMessage(ResponseStatus.successMessage);
				responseData.setData(userService.getAll());
				System.out.println("Users are  ==>>>" + userService.getAll().toString());
			} else {
				responseData.setStatus(ResponseStatus.fail);
				responseData.setMessage(ResponseStatus.failMessage);
			}
		}
		return responseData;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseData updateUser(@RequestBody UserCommand userCommand, @PathVariable Long id) {

		ResponseData responseData = new ResponseData();
		if (userService.updateUser(id, userCommand)) {
			responseData.setStatus(ResponseStatus.success);
			responseData.setMessage(ResponseStatus.successMessage);
		} else {
			responseData.setStatus(ResponseStatus.notFound);
			responseData.setMessage(ResponseStatus.notFoundMessage);
		}
		return responseData;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
	public ResponseData updateField(@RequestBody UserCommand userCommand, @PathVariable Long id) {

		ResponseData responseData = new ResponseData();

		if (userService.updateField(id, userCommand)) {
			responseData.setStatus(ResponseStatus.success);
			responseData.setMessage(ResponseStatus.successMessage);
		} else {
			responseData.setStatus(ResponseStatus.notFound);
			responseData.setMessage(ResponseStatus.notFoundMessage);
		}
		return responseData;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseData deleteUser(@RequestBody UserCommand userCommand, @PathVariable Long id) {

		ResponseData responseData = new ResponseData();

		if (userService.deleteUser(id, userCommand)) {
			responseData.setStatus(ResponseStatus.success);
			responseData.setMessage(ResponseStatus.successMessage);
		} else {
			responseData.setStatus(ResponseStatus.notFound);
			responseData.setMessage(ResponseStatus.notFoundMessage);
		}
		return responseData;
	}

}
