package com.gxb.sites.api.resource.user.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.user.User;
import com.gxb.sites.api.annotation.LcmsRepository;

/**
 * Created by Administrator on 2016-10-14.
 */
@LcmsRepository
public interface UserDao extends BasicDao<User> {
    User getByMobile(String mobile);

    int countByMobile(String mobile);
}
