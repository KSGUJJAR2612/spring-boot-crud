
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Role;
import com.example.demo.commandobject.RoleCommand;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService
{
	@Autowired
	private RoleRepository roleRepository;


	public boolean save(RoleCommand roleCommand)
	{

		Role role = new Role();
		role.setRoleName(roleCommand.getName());
		role.setDesc(roleCommand.getDesc());
		try
		{
			roleRepository.save(role);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
