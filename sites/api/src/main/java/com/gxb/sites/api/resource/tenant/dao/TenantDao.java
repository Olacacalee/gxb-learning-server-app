package com.gxb.sites.api.resource.tenant.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.tenant.Tenant;
import com.gxb.sites.api.annotation.LcmsRepository;

/**
 * Created by xing on 2017/9/30.
 */
@LcmsRepository
public interface TenantDao extends BasicDao<Tenant> {

    Tenant getByShortname(String shortname);

}
