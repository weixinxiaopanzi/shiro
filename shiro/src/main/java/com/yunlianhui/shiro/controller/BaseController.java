package com.yunlianhui.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import com.yunlianhui.shiro.entity.SysUser;


/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-05-02 15:32:55
 * @version V1.0
 */
public class BaseController {

	public static final String CURRENTUSER = "loginUser";
	/**
	 * 当前登录用户
	 * */
	public SysUser getLoginUser(HttpServletRequest request) {
		SysUser loginUser = (SysUser) request.getSession().getAttribute(CURRENTUSER);
		return loginUser;
	}
}
