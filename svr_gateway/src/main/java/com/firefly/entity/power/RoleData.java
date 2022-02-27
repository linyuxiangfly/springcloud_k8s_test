package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

/**
 * @Description 角色功能权限
 * @author Firefly
 */
public class RoleData extends BaseTable {
	private Role role;
	//数据权限
	private String data;
	//数据权限类型
	private String type;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
