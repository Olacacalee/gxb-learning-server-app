package com.gxb.sites.api.resource.userinfo.controller;

import com.gxb.sites.api.resource.userinfo.service.UserInfoService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import com.gxb.sites.api.sys.auth.UserContent;
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
public class UserInfoCtl {
    @Autowired
    private UserInfoService userinfoService;


    @RequestMapping(value = "/userinfo/getuserinfo", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map<String, Object> getUserInfo(String unionid){
        return userinfoService.getUserInfoByUserid(unionid);
    }

    /**
     * 申请学分
     * @return
     */
    @RequestMapping(value = "/userinfo/applyScore", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> applyScore(){
        return userinfoService.applyScore(UserContent.getUser().getUserId());
    }

    /**
     * 验证是否可以查看报告
     * @return
     */
    @RequestMapping(value = "/userinfo/checkReport", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> checkReport(){
        return userinfoService.checkReport(UserContent.getUser().getUserId());
    }

    /**
     * 获取报告
     * @return
     */
    @RequestMapping(value = "/userinfo/getReport", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getReport(){
        return userinfoService.getReport(UserContent.getUser().getUserId());
    }


    /**
     * 微信登录
     * @return
     */
    @RequestMapping(value = "/login/wxlogin", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map<String, Object> wxLogin(String code){

        Map<String,Object> map = userinfoService.wxLogin(code);
       return map;
    }

    @RequestMapping(value = "/wechat/getSign", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public String getSign(String url){
        return userinfoService.getSign(url);
    }

    @RequestMapping(value = "/wechat/getRequestCodeUrl", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public String getRequestCodeUrl(String url){
        return userinfoService.getRequestCodeUrl(url);
    }


    /**
     * 解锁下一章节
     * @return
     */
    @RequestMapping(value = "/update/updatelock", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public int updateUserLock(int lock,String unionid){
        return userinfoService.updateUserLock(lock,unionid);
    }

    @RequestMapping(value = "/userinfo/getReportById", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map getReportById(Long id){
        return userinfoService.getReportById(id);
    }


    @RequestMapping(value = "/userinfo/getUserInfoByUnionid", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map<String,Object> getUserInfoByUnionid(String unionid) {
        return userinfoService.getUserInfoByUnionid(unionid);
    }
}
