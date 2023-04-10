package com.logisticsystembackend.shrio;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @projectName: blog
 * @package: com.carolyn.shrio
 * @className: JwtToken
 * @author: Carolyn
 * @description: JwtToken
 * @date: 2023/3/2 9:45
 */

public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}
