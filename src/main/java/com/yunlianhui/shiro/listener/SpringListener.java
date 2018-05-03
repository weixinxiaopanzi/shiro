package com.yunlianhui.shiro.listener;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.yunlianhui.shiro.shiroConfig.ShiroRealm;

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
 * @date 2018-04-27 16:49:44
 * @version V1.0
 */
@Component
public class SpringListener {

	@Bean("authorityRealm")
	public ShiroRealm getUserRealm() {
		ShiroRealm userRealm = new ShiroRealm();
		return userRealm;
	}

	@Bean("securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(getUserRealm());
		manager.setCacheManager(new MemoryConstrainedCacheManager());
		return manager;
	}

//	@Bean("scheduler")
//	public Scheduler getScheduler() {
//		Scheduler scheduler = new Scheduler();
//		return scheduler;
//	}

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
		AuthorizingRealm realm = (AuthorizingRealm) context.getBean("authorityRealm");
//		Scheduler scheduler = (Scheduler) context.getBean("scheduler");
		// realm.setCredentialsMatcher(new CustomCredentialsMatcher());
		manager.setRealm(realm);
	}
}
