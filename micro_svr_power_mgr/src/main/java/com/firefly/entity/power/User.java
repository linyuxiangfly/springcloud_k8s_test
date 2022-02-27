package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pw_user")
public class User extends BaseTable {
	@Column(length = 30, unique = true)
	private String name;
	@Column(length = 15, unique = true)
	private String tel;//手机
	@Column
	private boolean gender;
	@Column(length = 60)
	private String type;
	@Column
	private boolean superUser;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}
}
