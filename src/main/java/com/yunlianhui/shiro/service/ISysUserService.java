package com.yunlianhui.shiro.service;

import com.yunlianhui.shiro.entity.SysUser;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-04-27 17:07:56
 * @version V1.0
 */
public interface ISysUserService {

	/**
	 * @param primaryPrincipal
	 * @return
	 */
	SysUser selectByUsername(String primaryPrincipal);
}
