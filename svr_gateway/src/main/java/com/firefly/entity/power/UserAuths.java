package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

public class UserAuths extends BaseTable {
	//绑定的用户
	private User user;

	//身份类型，如系统用户、微信用户、微博用户等
	private String identityType;

	//身份识别，不同身份类型的身份识别，如果登录名、微信openId等
	private String identityFier;

	//凭据,密码、token等，如果是系统用户则使用密码，微信用户使用token等
	private String credential;

	//是否验证身份，主要是每种登录身份类型绑定关系
	private boolean ifVerified;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityFier() {
		return identityFier;
	}

	public void setIdentityFier(String identityFier) {
		this.identityFier = identityFier;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public boolean isIfVerified() {
		return ifVerified;
	}

	public void setIfVerified(boolean ifVerified) {
		this.ifVerified = ifVerified;
	}
}
