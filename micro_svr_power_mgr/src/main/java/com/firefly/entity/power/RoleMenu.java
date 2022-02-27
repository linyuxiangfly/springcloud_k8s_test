package com.firefly.entity.power;


import com.firefly.entity.BaseTable;

import javax.persistence.*;

/**
 * @Description 角色与菜单绑定关系
 * @author Firefly
 */
@Entity
@Table(
		name = "pw_role_menu",
		uniqueConstraints = {
				@UniqueConstraint(columnNames={"role_id", "menu_id"})
		}
)
public class RoleMenu extends BaseTable {
	//功能
	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;

	//菜单
	@ManyToOne
	@JoinColumn(nullable = false)
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
