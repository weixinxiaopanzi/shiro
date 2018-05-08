package com.yunlianhui.shiro.listener;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
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
	@DependsOn("lifecycleBeanPostProcessor")
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
	
	@Bean
	public HashedCredentialsMatcher getHashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	/**
     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
     * 
     * @return
     */
//    @Bean(name = "ehCacheManager")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public EhcacheManager ehCacheManager() {
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//        return cacheManager;
//    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }
    /**
     * cookie管理对象;
     * 
     * @return
     */
    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

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
