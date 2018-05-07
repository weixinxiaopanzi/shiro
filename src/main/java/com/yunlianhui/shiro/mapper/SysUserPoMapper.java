package com.yunlianhui.shiro.mapper;

import com.yunlianhui.shiro.entity.SysUser;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-04-25 15:26:39
 * @version V1.0
 */
public interface SysUserPoMapper extends SysUserMapper{

	public SysUser selectByUsername(String userName);
}
