
package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private Integer id;

	private String roleName;

	@Column(name = "descrption")
	private String desc;

	@JsonIgnore
	@ManyToMany(mappedBy = "role")
	private Set<User> user;


	public Integer getId()
	{

		return id;
	}


	public Set<User> getUser()
	{

		return user;
	}


	public void setUser(Set<User> user)
	{

		this.user = user;
	}


	public String getDesc()
	{

		return desc;
	}


	public void setDesc(String desc)
	{

		this.desc = desc;
	}


	public String getRoleName()
	{

		return roleName;
	}


	public void setRoleName(String roleName)
	{

		this.roleName = roleName;
	}

}
