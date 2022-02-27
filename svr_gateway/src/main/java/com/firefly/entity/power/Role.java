package com.firefly.entity.power;

import com.firefly.entity.BaseTable;
/**
 * @Description 角色
 * @author Firefly
 */
public class Role extends BaseTable {
	private String tag;//分类
	//角色名称
	private String name;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
