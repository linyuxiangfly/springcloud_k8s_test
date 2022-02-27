package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

import javax.persistence.*;

/**
 * @Description 功能类
 * @author Firefly
 */
@Entity
@Table(
		name = "pw_func",
//		uniqueConstraints = {
//				@UniqueConstraint(columnNames={"url", "method"})
//		},
		indexes = {
				@Index(name = "index_url_method",  columnList="url,method", unique = true)
		}
)
public class Func extends BaseTable {
	//功能名称
	@Column(length = 40)
	private String name;
	//功能的地址
//	@Column(unique = true)
	private String url;
	//方法如GET、POST、DELETE、PUT等
	@Column(length = 10)
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
