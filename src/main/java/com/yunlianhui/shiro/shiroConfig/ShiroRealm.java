package com.yunlianhui.shiro.shiroConfig;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.yunlianhui.shiro.entity.SysUser;
import com.yunlianhui.shiro.service.ISysUserService;


/**
 * <p>
 * Copyright (c) 广东云联国骥投资管理有限公司
 * </p>
 *
 * <p>
 * Title: Shiro支持功能
 * </p>
 *
 * @author Maopz
 * @date 2018-04-25 15:21:57
 * @version V1.0
 */
public class ShiroRealm extends AuthorizingRealm {

	// 日志
//	Logger logger = Logger.getLogger("stdout");

	@Resource
	private ISysUserService sysUserService;

	/*
	 * @Resource public SysRoleMenuService sysRoleMenuService;
	 */

	// 获取用户的权限字符给到shiro进行权限验证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->doGetAuthorizationInfo");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		String userName = (String) principals.getPrimaryPrincipal();
//		SysUser loginUser = sysUserService.selectByUsername(userName);
		// 获取用户的权限字符 添加进去
		/*
		 * List<String> perList =
		 * sysRoleMenuService.queryAllPerByUserId(loginUser.getId());
		 * if(perList!=null&&perList.size()>0){ logger.info("用户权限："+perList); for(String
		 * per:perList){ authorizationInfo.addStringPermission(per.trim()); } }
		 */
		return authorizationInfo;
	}

	// 获取用户信息方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials()); // 得到密码
		
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		SysUser userInfo = sysUserService.selectByUsername(userName);

		if (userInfo == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
//		if (!userInfo.getStatus().equals(UM.STATUS_1)) {
//			throw new LockedAccountException(); // 帐号已禁用
//		}

		if (!userInfo.getPassword().equals(password)) {
			throw new IncorrectCredentialsException(); // 密码错误
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo.getUserName(), // 用户名
				userInfo.getPassword(), // 密码
				ByteSource.Util.bytes("abc123"), 
				getName() // realm name
		);

		return authenticationInfo;
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}
