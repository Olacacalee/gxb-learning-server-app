package com.gxb.sites.api.resource.user.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.user.User;
import com.gxb.sites.api.annotation.LcmsRepository;

/**
 * Created by xing on 2017/9/21.
 */
@LcmsRepository
public interface UserDao extends BasicDao<User> {
    User getByUsername(String username);
}
