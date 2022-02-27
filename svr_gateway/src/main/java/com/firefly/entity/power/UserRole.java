package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

/**
 * @Description 用户角色权限
 * @author Firefly
 */
public class UserRole extends BaseTable {
	//用户
	private User user;
	//角色
	private Role role;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
