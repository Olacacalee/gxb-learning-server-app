package com.gxb.sites.api.resource.user.app.controller;

import com.gxb.modules.domain.student.Student;
import com.gxb.modules.domain.user.User;
import com.gxb.modules.utils.StringTools;
import com.gxb.sites.api.resource.user.service.UserService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import com.gxb.sites.api.sys.auth.UserContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Administrator on 2016-10-14.
 */
@Controller
public class UserCtl {
    @Autowired
    private UserService userService;

    /**
     * 检查是否是新用户
     * @param openId
     * @param unionId
     * @return
     */
    @RequestMapping(value = "/user/checkOpenId", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map checkOpenId(String openId,String unionId){
        return userService.checkOpenId(openId,unionId);
    }

    /**
     * 获取手机验证码
     * @param mobile
     * @param type
     * @return
     */
    @RequestMapping(value = "/user/getMobileCode", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map getMobileCode(String mobile,String type){
        return userService.getMobileCode(mobile,type);
    }

    /**
     * 验证手机验证码
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/checkMobileCode", method = RequestMethod.POST)
    @AccessTokenCheck(false)
    @ResponseBody
    public Map checkMobileCode(@RequestBody User user){
        return userService.checkMobileCode(user);
    }

    /**
     * 手机号没有注册，创建账号
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map register(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 学生认证
     * @param student
     * @param access_token
     * @return
     */
    @RequestMapping(value = "/user/binding", method = RequestMethod.POST)
    @ResponseBody
    public Map bindingStudent( @RequestBody Student student,String access_token) {

        Map result = userService.bindingSchool(StringTools.getLong(UserContent.getUser().getUserId()),student);
        if("0".equals(result.get("status"))){
            userService.updateTenantIdRedis(access_token,StringTools.getString(student.getTenantId()));
        }
        return result;
    }

    
    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map getUserInfo(){
        return userService.getUserInfo(StringTools.getLong(UserContent.getUser().getUserId()));
    }
}
