package com.gxb.sites.api.resource.tenant.service;

import com.gxb.modules.domain.result.ResultObject;
import com.gxb.modules.domain.tenant.Tenant;
import com.gxb.sites.api.resource.tenant.dao.TenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xing on 2017/9/30.
 */
@Service
public class TenantService {

    @Autowired
    private TenantDao tenantDao;

    public ResultObject getByShortname(String shortname){
        return new ResultObject("1","成功",tenantDao.getByShortname(shortname));
    }

}
