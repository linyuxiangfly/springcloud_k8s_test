package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

import javax.persistence.*;

/**
 * @Description 用户角色权限
 * @author Firefly
 */
@Entity
@Table(
		name = "pw_user_role",
		uniqueConstraints = {
				@UniqueConstraint(columnNames={"user_id", "role_id"})
		}
)
public class UserRole extends BaseTable {
	//用户
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	//角色
	@ManyToOne
	@JoinColumn(nullable = false)
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
