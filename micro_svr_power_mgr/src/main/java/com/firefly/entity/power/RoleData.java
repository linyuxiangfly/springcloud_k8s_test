package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

import javax.persistence.*;

/**
 * @Description 角色功能权限
 * @author Firefly
 */
@Entity
@Table(
		name = "pw_role_data",
		uniqueConstraints = {
				@UniqueConstraint(columnNames={"role_id", "data", "type"})
		}
)
public class RoleData extends BaseTable {
	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;
	//数据权限
	@Column(length = 35)
	private String data;
	//数据权限类型
	@Column(length=60)
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
