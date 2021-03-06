package com.yunlianhui.shiro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunlianhui.shiro.entity.SysUser;
import com.yunlianhui.shiro.mapper.SysUserPoMapper;
import com.yunlianhui.shiro.service.ISysUserService;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-04-27 17:08:42
 * @version V1.0
 */
@Service
public class SysUserServiceImpl implements ISysUserService{

	@Resource
	public SysUserPoMapper sysUserPoMapper;
	
	/* (non-Javadoc)
	 * @see com.yunlianhui.shiro.service.ISysUserService#selectByUsername(java.lang.String)
	 */
	@Override
	public SysUser selectByUsername(String primaryPrincipal) {
		return sysUserPoMapper.selectByUsername(primaryPrincipal);
	}

	/* (non-Javadoc)
	 * @see com.yunlianhui.shiro.service.ISysUserService#updateById(com.yunlianhui.shiro.entity.SysUser)
	 */
	@Override
	public int updateById(SysUser sysUser) {
		return sysUserPoMapper.updateByPrimaryKeySelective(sysUser);
	}

}
