package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

import javax.persistence.*;

/**
 * @Description 角色
 * @author Firefly
 */
@Entity
@Table(
		name = "pw_role",
		uniqueConstraints = {
				@UniqueConstraint(columnNames={"tag", "name"})
		}
)
public class Role extends BaseTable {
	@Column(length=35)
	private String tag;//分类
	//角色名称
	@Column(length=30)
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
