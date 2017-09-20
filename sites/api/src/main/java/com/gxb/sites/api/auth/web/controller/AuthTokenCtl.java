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

//    @RequestMapping("/token/{accessToken}")
//    @ResponseBody
    public AuthToken token(String accessToken,String type){
        String tokenPrefix = DomainConstants.GXB_WECHAT_TOKEN_FREFIX;
        accessToken = tokenPrefix + accessToken;
        AuthToken authToken;
        authToken = (AuthToken) redisCacheService.getObject(accessToken);

        /*if (authToken==null){
            authToken = authTokenService.loadToken(accessToken);
            if (authToken!=null){//数据库有token
                if (authToken.getDeadTime()!=null){//有失效期
                    if (authToken.getDeadTime().getTime() > new Date().getTime()){//还没失效
                        redisCacheService.putObject(accessToken, authToken);
                    }else {//失效了
                        authTokenService.remove(accessToken);
                        authToken = null;
                    }
                }else {//无失效期
                    redisCacheService.putObject(accessToken, authToken);
                }

            }
        }*/
        if (authToken==null){
            authToken=new AuthToken();
        }
        return authToken;
    }
}
