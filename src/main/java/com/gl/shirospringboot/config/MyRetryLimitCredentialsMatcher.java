package com.gl.shirospringboot.config;

import com.gl.shirospringboot.utils.LoginType;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @author guoliang
 */
public class MyRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomToken customToken = (CustomToken) token;
        if (customToken.getType().equals(LoginType.NOPASSWORD)) {
            return true;
        }
        return super.doCredentialsMatch(token, info);
    }
}
