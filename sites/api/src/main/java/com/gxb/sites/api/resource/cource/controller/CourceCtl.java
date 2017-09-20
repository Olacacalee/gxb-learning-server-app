package com.gxb.sites.api.resource.cource.controller;

import com.gxb.modules.domain.user.User;
import com.gxb.sites.api.resource.cource.service.CourceService;
import com.gxb.sites.api.resource.userinfo.dao.UserInfoDao;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-14.
 */
@Controller
public class CourceCtl {
    @Autowired
    private CourceService courceService;



    @RequestMapping(value = "/cource/getCource", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map<String, Object> getMobileCode(String unionid,int id){

        return courceService.getCourceByparentId(unionid,id);
    }



}
