package com.gxb.sites.api.resource.tenant.app.controller;

import com.gxb.modules.domain.result.ResultObject;
import com.gxb.sites.api.resource.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xing on 2017/9/30.
 */
@Controller
public class TenantCtl {

    @Autowired
    private TenantService tenantService;

    @RequestMapping(value = "/tenant/shortname", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject getByShortname(String shortname){
        return tenantService.getByShortname(shortname);
    }

}
