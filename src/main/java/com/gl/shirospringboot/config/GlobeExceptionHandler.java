package com.gl.shirospringboot.config;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author guoliang
 * @date 2022/2/4/0004 13:42
 */
@RestControllerAdvice
public class GlobeExceptionHandler {
    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public String globalException() {
        return "认证未通过";
    }
}
