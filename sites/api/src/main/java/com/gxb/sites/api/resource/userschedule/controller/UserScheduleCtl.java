package com.gxb.sites.api.resource.userschedule.controller;

import com.gxb.sites.api.resource.userschedule.service.UserScheduleService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016-10-14.
 */
@Controller
public class UserScheduleCtl {
    @Autowired
    private UserScheduleService userscheduleservice;




    @RequestMapping(value = "/schedule/updateschedule", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public int updateschedule(String unionid,int schedule,int courceid){
        return userscheduleservice.updateSchedule(unionid,schedule,courceid);
    }


}
