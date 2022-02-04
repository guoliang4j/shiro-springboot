package com.gl.shirospringboot.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.crazycake.shiro.RedisManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author guoliang
 */
@Slf4j
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {
    //存入Redis中的SessionID的前缀
    private static final String PREFIX = "SHIRO_SESSION_ID";
    //有效期（后续使用时会增加时间单位）
    private static final int EXPRIE = 1200;
    //Redis 操作工具
    @Resource
    private RedisTemplate<Serializable, Session> redisTemplate;


    /**
     * shiro创建session时，将session保存到redis
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        //生成SessionID
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        //将sessionid作为Key，session作为value存入redis
        redisTemplate.opsForValue().set(serializable, session);
        log.debug("用户session保存到redis中，sessionId为" + serializable);
        return serializable;
    }

    /**
     * 当用户维持会话时，刷新session的有效时间
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        //设置session有效期
        session.setTimeout(EXPRIE * 1000);
        //将sessionid作为Key，session作为value存入redis，并设置有效期
        redisTemplate.opsForValue().set(session.getId(), session, EXPRIE, TimeUnit.SECONDS);
    }

    /**
     * 当用户注销或会话过期时，将session从redis中删除
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        //null 验证
        if (session == null) {
            return;
        }
        //从Redis中删除指定SessionId的k-v
        redisTemplate.delete(session.getId());
    }

    /**
     * shiro通过sessionId获取Session对象，从redis中获取
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        //从Redis中读取Session对象
        Session session = redisTemplate.opsForValue().get(sessionId);
        System.out.println("从redis中读取session对象，id为: " + sessionId);
        return session;
    }

    public void setRedisManager(RedisManager redisManager) {
    }
}
