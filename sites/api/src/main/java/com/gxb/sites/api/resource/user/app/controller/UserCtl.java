package com.gxb.sites.api.resource.user.app.controller;

import com.gxb.modules.domain.result.ResultObject;
import com.gxb.modules.domain.user.User;
import com.gxb.sites.api.resource.user.service.UserService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xing on 2017/9/21.
 */
@Controller
public class UserCtl {
    @Autowired
    private UserService userService;

    /**
     * 检验是否登录
     * @return
     */
    @RequestMapping(value = "/user/check", method = RequestMethod.GET)
    @ResponseBody
    public String check(){
        return "ok";
    }

    /**
     * 检查是否是新用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    @AccessTokenCheck(false)
    public ResultObject login(@RequestBody User user){
        return userService.login(user);
    }

}
