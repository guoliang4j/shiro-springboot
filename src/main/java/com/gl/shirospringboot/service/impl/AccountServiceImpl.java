package com.gl.shirospringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gl.shirospringboot.mapper.AccountMapper;
import com.gl.shirospringboot.pojo.Account;
import com.gl.shirospringboot.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guoliang
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        return accountMapper.selectOne(wrapper);
    }

    @Override
    public boolean insertAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        accountMapper.insert(account);
        return true;
    }
}
