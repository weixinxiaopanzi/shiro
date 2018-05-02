package com.yunlianhui.shiro.shiroConfig;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * <p>
 * Copyright (c) 广东云联国骥投资管理有限公司
 * </p>
 *
 * <p>
 * Title:shiro配置类
 * </p>
 *
 * @author Maopz
 * @date 2018-04-27 15:05:54
 * @version V1.0
 */
@Configuration
public class ShiroConfig {
	
	@Bean
    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断o
        filterChainDefinitionMap.put("/user/login.do", "anon");//登录
        filterChainDefinitionMap.put("/js/**", "anon");
        
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/images/**", "anon");
//        filterChainDefinitionMap.put("/scss/**", "anon");
//        filterChainDefinitionMap.put("/vendors/**", "anon");
//        filterChainDefinitionMap.put("/view/**", "anon");
//        filterChainDefinitionMap.put("/api/**", "anon");
        
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/sys/sysUserList");
        //未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/static/403");
       
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

   
    	
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
    
    //未授权异常界面
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
    	SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
    	Properties properties =new Properties();
    	//properties.put("UnauthorizedException", "403");
    	properties.put("UnauthorizedException", "redirect:/403");
    	exceptionResolver.setExceptionMappings(properties);
        return exceptionResolver;
    }

}
