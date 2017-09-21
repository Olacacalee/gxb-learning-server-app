package com.gxb.sites.api.auth.web.controller;

import com.gxb.modules.cache.service.RedisCacheService;
import com.gxb.modules.constants.DomainConstants;
import com.gxb.modules.core.web.controller.BasicCtl;
import com.gxb.modules.domain.auth.AuthToken;
import com.gxb.sites.api.auth.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * time : 15/11/5.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class AuthTokenCtl extends BasicCtl {

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private RedisCacheService redisCacheService;

    public AuthToken token(String accessToken){
        String tokenPrefix = DomainConstants.CAR_TOKEN_SURFIX;
        accessToken = tokenPrefix + accessToken;
        AuthToken authToken;
        authToken = (AuthToken) redisCacheService.getObject(accessToken);

        if (authToken==null){
            authToken=new AuthToken();
        }
        return authToken;
    }
}
