package com.yunlianhui.shiro.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunlianhui.shiro.entity.SysUser;
import com.yunlianhui.shiro.service.ISysUserService;
import com.yunlianhui.shiro.util.ApiResponse;
import com.yunlianhui.shiro.util.CipherUtil;
import com.yunlianhui.shiro.util.UM;

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
 * @date 2018-04-27 16:55:55
 * @version V1.0
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController{

	@Resource
	private ISysUserService sysUserService;

	@GetMapping("getLoginUser.do")
	public SysUser getCurrentUser(HttpServletRequest request) {
		return getLoginUser(request);
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "login";
	}

	/**
	 * 验证用户名和密码
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public ApiResponse login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse();
		String userName = request.getParameter("userName");// 取得用户名
		String password = request.getParameter("password");// 取得 密码，并用MD5加密
		password = CipherUtil.generatePassword(password);
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		Subject currentUser = SecurityUtils.getSubject();
		String errorName = "";
		try {
			if (currentUser.isAuthenticated()) {// 是否已经登录过
				currentUser.logout();// 清空缓存
			}
			token.setRememberMe(true);
			currentUser.login(token);// 验证角色和权限,会在ShiroRealm中校验
		} catch (AuthenticationException e) {
			errorName = e.getClass().getName();
		}
		if (UnknownAccountException.class.getName().equals(errorName)) {
			// 用户或密码错误, 请重试
			apiResponse.setCodeAndMessage(UM.CODE_LOGINFAIL_0001, UM.MESSAGE_LOGINFAIL_0001);
		} else if (IncorrectCredentialsException.class.getName().equals(errorName)) {
			// 密码错误, 请重试
			apiResponse.setCodeAndMessage(UM.CODE_LOGINFAIL_0002, UM.MESSAGE_LOGINFAIL_0002);
		} else if (LockedAccountException.class.getName().equals(errorName)) {
			// 账号已禁用";
			apiResponse.setCodeAndMessage(UM.CODE_LOGINFAIL_0003, UM.MESSAGE_LOGINFAIL_0003);
		} else if (ExcessiveAttemptsException.class.getName().equals(errorName)) {
			// 请勿重复提交认证,请半小时之后登录";
			apiResponse.setCodeAndMessage(UM.CODE_LOGINFAIL_0004, UM.MESSAGE_LOGINFAIL_0004);
		} else {
			// 登录用户存入session
			SysUser loginUser = sysUserService.selectByUsername(userName);
			request.getSession().setAttribute(CURRENTUSER, loginUser);
			request.getSession().setAttribute("loginUserName", loginUser.getUserName());
			request.getSession().setMaxInactiveInterval(3600);

			// 登录成功
			apiResponse.setCodeAndMessage(UM.CODE_SUCCESS_0000, UM.MESSAGE_SUCCESS_0000);
			apiResponse.putData("id", loginUser.getId());
			apiResponse.putData("userName", userName);
		}
		return apiResponse;
	}
}
