package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

import javax.persistence.*;

/**
 * @Description 菜单与接口绑定关系
 * @author Firefly
 */
@Entity
@Table(
		name = "pw_menu_func",
		uniqueConstraints = {
				@UniqueConstraint(columnNames={"menu_id", "func_id"})
		},
		indexes = {
				@Index(name = "index_menu",  columnList="menu_id", unique = false),
				@Index(name = "index_func",  columnList="func_id", unique = false)
		}
)
public class MenuFunc extends BaseTable {
	//菜单
	@ManyToOne
	@JoinColumn(nullable = false)
	private Menu menu;
	//功能
	@ManyToOne
	@JoinColumn(nullable = false)
	private Func func;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Func getFunc() {
		return func;
	}
	public void setFunc(Func func) {
		this.func = func;
	}

	
}
