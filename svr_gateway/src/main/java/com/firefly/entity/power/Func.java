package com.firefly.entity.power;

import com.firefly.entity.BaseTable;
/**
 * @Description 功能类
 * @author Firefly
 */
public class Func extends BaseTable {
	//功能名称
	private String name;
	//功能的地址
	private String url;
	//方法如GET、POST、DELETE、PUT等
	private String method;
	//是否为正则匹配
	private boolean regex=false;
	
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRegex() {
		return regex;
	}

	public void setRegex(boolean regex) {
		this.regex = regex;
	}
}
