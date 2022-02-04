package com.gl.shirospringboot.controller;

import com.gl.shirospringboot.config.CustomToken;
import com.gl.shirospringboot.pojo.Account;
import com.gl.shirospringboot.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author guoliang
 */
@RestController
@Slf4j
public class AccountController {
    @Resource
    AccountService accountService;

    @GetMapping("/account/register")
    public String register(String username, String password) {
        SimpleHash simpleHash = new SimpleHash("MD5", password, null, 1024);
        boolean b = accountService.insertAccount(username, simpleHash.toString());
        return "注册成功";
    }

    @GetMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        CustomToken token = new CustomToken(username, password);
        try {
            subject.login(token);
            return "index";
        } catch (AuthenticationException e) {
            throw new AuthenticationException();
        }
    }

    @GetMapping("/no_password_login")
    public String noPasswordLogin(String username) {
        Subject subject = SecurityUtils.getSubject();
        CustomToken token = new CustomToken(username);
        log.info(token.getPrincipal().toString());
        try {
            subject.login(token);
            return "index";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @GetMapping("/logout")
    public Account logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return (Account) subject.getPrincipal();
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/manage")
    public String manage() {
        return "manage";
    }

    @GetMapping("/administrator")
    public String administrator() {
        return "administrator";
    }

    @GetMapping("/account")
    public Account getCurrentAccountInfo() {
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        account.setSession((String) subject.getSession().getId());
        return account;
    }
}
