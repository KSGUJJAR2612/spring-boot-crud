
package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.commandobject.UserCommand;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public boolean save(UserCommand usercommand) {

		User user = new User();
		user.setName(usercommand.getName());
		user.setEmail(usercommand.getEmail());

		List<Integer> roleIds = usercommand.getRoleIds();

		Set<Role> roles = null;
		if (roleIds != null) {
			roles = new HashSet<>();

			for (int roleid : roleIds) {
				Role role = roleRepository.findOne(roleid);
				if (role != null) {
					roles.add(role);
				}
			}
		}
		user.setRole(roles);
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<UserCommand> getAll() {

		Iterable<User> users = userRepository.findAll();

		List<UserCommand> userCommands = new ArrayList<>();

		for (User user : users) {
			UserCommand userCommand = new UserCommand();
			userCommand.setId(user.getId());
			userCommand.setName(user.getName());
			userCommand.setEmail(user.getEmail());

			Set<String> rolenames = new HashSet<>();
			for (Role role : user.getRole()) {
				rolenames.add(role.getRoleName());
			}
			userCommand.setRoleNames(rolenames);

			userCommands.add(userCommand);
		}
		return userCommands;
	}

	public UserCommand getUserById(Long userID) {

		UserCommand userCommand = null;

		User user = userRepository.findOne(userID);
		if (user != null) {
			userCommand = new UserCommand();
			userCommand.setId(user.getId());
			userCommand.setName(user.getName());
			userCommand.setRoles(user.getRole());
			userCommand.setEmail(user.getEmail());

			Set<String> rolenames = new HashSet<>();
			for (Role role : user.getRole()) {
				rolenames.add(role.getRoleName());
			}
			userCommand.setRoleNames(rolenames);

		}

		return userCommand;
	}

	public Object getUserByRole(String roleName) {

		List<Role> roles = roleRepository.getUserByRole(roleName);

		List<UserCommand> usercommands = new ArrayList<>();
		for (Role role : roles) {
			Set<User> users = role.getUser();
			for (User user : users) {
				UserCommand userCommand = new UserCommand();
				userCommand.setId(user.getId());
				userCommand.setName(user.getName());

				usercommands.add(userCommand);
			}

		}

		// userCommand.setUsers(allusers);
		return usercommands;
	}

	public List<UserCommand> getUserByOffset(Pageable pageable) {

		Page<User> users = userRepository.findAll(pageable);
		List<UserCommand> usercommandlist = new ArrayList<>();

		for (User user : users) {
			UserCommand userCommand = new UserCommand();
			userCommand.setId(user.getId());
			userCommand.setName(user.getName());
			usercommandlist.add(userCommand);
		}

		return usercommandlist;
	}

	public boolean updateUser(Long id, UserCommand userCommand) {

		User user = userRepository.findOne(id);
		if (user != null) {

			user.setName(userCommand.getName());

			List<Integer> roleIds = userCommand.getRoleIds();
			Set<Role> roles = null;
			if (roleIds != null) {
				roles = new HashSet<>();

				for (int roleid : roleIds) {
					Role role = roleRepository.findOne(roleid);
					if (role != null) {
						roles.add(role);
					}
				}
			}
			user.setRole(roles);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public boolean updateField(Long id, UserCommand userCommand) {

		User user = userRepository.findOne(id);
		if (user != null) {
			if (userCommand.getName() != null) {
				user.setName(userCommand.getName());
			}
			if (userCommand.getEmail() != null) {
				user.setEmail(userCommand.getEmail());
			}
			if (userCommand.getRoles() != null) {
				List<Integer> roleIds = userCommand.getRoleIds();

				Set<Role> roles = new HashSet<>();

				for (int roleid : roleIds) {
					Role role = roleRepository.findOne(roleid);
					if (role != null) {
						roles.add(role);
					}
				}

				user.setRole(roles);
			}

			userRepository.save(user);
			return true;
		}
		return false;
	}

	public boolean deleteUser(Long id, UserCommand userCommand) {

		try {
			userRepository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
