
package com.example.demo.commandobject;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.utility.LengthConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserCommand {
    private Long id;

    @NotNull(message = "User name cann't be null")
    // @Length(min = 3, message = "Username must have at least 3 chars.")
    @NotEmpty(message = "User name cann't be empty")
    @Pattern(regexp = "[A-Z, a-z]{3}[a-z, A-Z, ]*", message = "Name can contain only alphabets ans spaces. Min size 3.")
    @LengthConstraint(message = "name must have number of chars bw 3 and 10")
    private String name;

    @NotEmpty(message = "Mail is required")
    @Email(message = "Mail should be in proper format")
    private String email;

    @JsonIgnore
    private List<Integer> roleIds;

    private Set<String> roleNames;

    @JsonIgnore
    private Set<Role> roles;
    @JsonIgnore
    private Set<User> users;


    public String getEmail() {

        return email;
    }


    public void setEmail(String email) {

        this.email = email;
    }


    public List<Integer> getRoleIds() {

        return roleIds;
    }


    public Set<String> getRoleNames() {

        return roleNames;
    }


    public void setRoleNames(Set<String> roleNames) {

        this.roleNames = roleNames;
    }


    public void setRoleIds(List<Integer> roleIds) {

        this.roleIds = roleIds;
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public Set<User> getUsers() {

        return users;
    }


    public void setUsers(Set<User> users) {

        this.users = users;
    }


    public void setRoles(Set<Role> roles) {

        this.roles = roles;
    }


    public Set<Role> getRoles() {

        return roles;
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }
}
