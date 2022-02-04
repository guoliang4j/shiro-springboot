package com.gl.shirospringboot.service;

import com.gl.shirospringboot.pojo.Account;

/**
 * @author guoliang
 */

public interface AccountService {
    Account findByUsername(String username);

    boolean insertAccount(String username, String password);
}
