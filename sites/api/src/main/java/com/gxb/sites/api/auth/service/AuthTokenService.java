package com.gxb.sites.api.auth.service;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.core.service.BasicService;
import com.gxb.modules.domain.auth.AuthToken;
import com.gxb.sites.api.auth.dao.AuthTokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * time : 15/11/5.
 * auth :
 * desc :
 * tips :
 * 1.
 */

@Service
public class AuthTokenService extends BasicService<AuthToken> {

    @Autowired
    private AuthTokenDao authTokenDao;


    public AuthToken loadToken(String accessToken) {
        return  authTokenDao.loadToken(accessToken);
    }

    @Override
    protected BasicDao<AuthToken> getDAO() {
        return authTokenDao;
    }

    public void remove(String accessToken) {
        authTokenDao.remove(accessToken);
    }
}
