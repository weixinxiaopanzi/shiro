package com.yunlianhui.shiro.entity;

import java.util.List;

/**
 * <p>
 * Copyright (c) 广东云联国骥投资管理有限公司
 * </p>
 *
 * <p>
 * Title:
 * </p>
 *
 * @author Maopz
 * @date 2018-04-25 15:32:56
 * @version V1.0
 */
public class SysUser {

	private Long id;
	private String userName;
	private String password;
	private List<SysRole> sysRoles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

}
