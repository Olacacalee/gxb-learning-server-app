package com.gxb.sites.api.resource.pathlogic.controller;

import com.gxb.sites.api.resource.pathlogic.service.PathlogicService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016-10-14.
 */
@Controller
public class PathlogicCtl {
    @Autowired
    private PathlogicService courceService;

    /**
     * 获取手机验证码
     * @param mobile
     * @param type
     * @return
     */



}
