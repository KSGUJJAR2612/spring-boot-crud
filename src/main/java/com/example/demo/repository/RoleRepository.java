package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;

public interface RoleRepository extends CrudRepository<Role, Integer>
{
	@Query("Select r from Role r where r.roleName =?")
   	public List<Role> getUserByRole(String role);
}
