package com.gxb.sites.api.auth.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.auth.AuthToken;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

/**
 * time : 15/11/5.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@LcmsRepository
public interface AuthTokenDao extends BasicDao<AuthToken> {
    AuthToken loadToken(String accessToken);

    void remove(@Param("token") String accessToken);
}
