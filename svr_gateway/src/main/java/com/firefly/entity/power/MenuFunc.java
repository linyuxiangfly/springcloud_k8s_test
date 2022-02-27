package com.firefly.entity.power;

import com.firefly.entity.BaseTable;


/**
 * @Description 菜单与接口绑定关系
 * @author Firefly
 */
public class MenuFunc extends BaseTable {
	//菜单
	private Menu menu;
	//功能
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
