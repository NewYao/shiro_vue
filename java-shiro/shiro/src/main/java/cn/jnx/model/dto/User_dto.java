package cn.jnx.model.dto;

import java.util.List;

import cn.jnx.model.Role;
import cn.jnx.model.User;

public class User_dto extends User {
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
