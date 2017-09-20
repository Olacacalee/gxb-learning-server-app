package com.gxb.sites.api.HelloWorld.controller;

import com.gxb.sites.api.HelloWorld.service.HelloWorldService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
@Controller
public class Helloworld {

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping(value = "/testConnection/api", method = RequestMethod.GET)
    @AccessTokenCheck(true)
    @ResponseBody
    public List testConnection(){
        return helloWorldService.test();
    }

    @RequestMapping(value = "/Helloworld/ChartArraySort", method = RequestMethod.GET)
    @AccessTokenCheck(false)
    @ResponseBody
    public String ChartArraySort(){
        return helloWorldService.ChartArraySort();
    }
}
