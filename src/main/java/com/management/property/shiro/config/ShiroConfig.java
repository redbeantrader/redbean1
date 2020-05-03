package com.management.property.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.management.property.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher =
                new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        return credentialsMatcher;
    }

    @Bean("userRealm")
    public ShiroRealm userRealm(@Qualifier("hashedCredentialsMatcher")
                                        HashedCredentialsMatcher matcher) {

        ShiroRealm userRealm = new ShiroRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean
    public ShiroDialect shiroDialect() {//shiro标签跳转bean
        return new ShiroDialect();
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
        // 设置登录成功跳转Url
        bean.setSuccessUrl("/main");
        // 设置登录跳转Url
        bean.setLoginUrl("/login");
        // 设置未授权提示Url
        bean.setUnauthorizedUrl("/NotSuccess");

        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/layui/**", "anon");
        filterMap.put("/login/**", "anon");
        filterMap.put("/JQuery/**", "anon");
        filterMap.put("/reset", "anon");
        filterMap.put("/GoToReset", "anon");
        filterMap.put("/ResetPage", "anon");
        filterMap.put("/Accessible","roles[root]");
        filterMap.put("/registered", "anon");
        //filterMap.put("/vip/index","roles[user]");
        //filterMap.put("/vip/index", "perms[user:vip]");
        filterMap.put("/logout", "logout");
        filterMap.put("/NoPermission", "authc");
        filterMap.put("/main", "authc");


        //anon 无拦截
        //authc 登录验证
        filterMap.put("/**", "anon");

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            HashedCredentialsMatcher hashedCredentialsMatcher) {

        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }

}
