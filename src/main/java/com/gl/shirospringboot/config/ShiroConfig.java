package com.gl.shirospringboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guoliang
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean filterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager());
        Map<String, String> map = new HashMap<>();
        map.put("/main", "authc");
        map.put("/logout", "authc");
        map.put("/manage", "perms[manage]");
        map.put("/administrator", "roles[administrator]");
        map.put("/no_password_login", "anon");
        //map.put("/login", "anon");
        map.put("/*", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面
        factoryBean.setLoginUrl("/login");

        //未授权页面
        //factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm());
        // 自定义缓存实现 使用redis
        manager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        manager.setSessionManager(sessionManager());
        return manager;
    }

    /**
     * Session Manager
     */
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * redisManager
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }

    /**
     * cacheManager
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * redisSessionDAO
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        //不配置则报错
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    @Bean
    public AccountRealm accountRealm() {
        AccountRealm accountRealm = new AccountRealm();
        accountRealm.setCredentialsMatcher(myRetryLimitCredentialsMatcher());
        return accountRealm;
    }

    @Bean
    public MyRetryLimitCredentialsMatcher myRetryLimitCredentialsMatcher() {
        MyRetryLimitCredentialsMatcher limitCredentialsMatcher = new MyRetryLimitCredentialsMatcher();
        limitCredentialsMatcher.setHashAlgorithmName("MD5");
        limitCredentialsMatcher.setHashIterations(1024);
        return limitCredentialsMatcher;
    }

   /* //容器中注册RedisSessionDao
    @Bean
    public SessionDAO redisSessionDAO() {
        return new RedisSessionDAO();
    }*/
}
