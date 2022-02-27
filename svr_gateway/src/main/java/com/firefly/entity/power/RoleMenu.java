package com.firefly.entity.power;


import com.firefly.entity.BaseTable;


/**
 * @Description 角色与菜单绑定关系
 * @author Firefly
 */
public class RoleMenu extends BaseTable {
	//功能
	private Role role;

	//菜单
	private Menu menu;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
}
