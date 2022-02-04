package com.gl.shirospringboot;

import com.gl.shirospringboot.mapper.AccountMapper;
import com.gl.shirospringboot.utils.ShiroUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ShiroSpringbootApplicationTests {
    @Resource
    AccountMapper accountMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        //保存
        redisTemplate.opsForValue().set("key-1", "value-1");
        //带有效期的保存
        //redisTemplate.opsForValue().set("key-1", "value-1", 120, TimeUnit.SECONDS);
        //删除
        //redisTemplate.delete("key-1");
    }

    @Test
    void md5() {
        String salt = ShiroUtil.createSalt();
        System.out.println(salt);
        SimpleHash simpleHash = new SimpleHash("MD5", "123123", salt, 1024);
        System.out.println(simpleHash.toString());

    }
}
