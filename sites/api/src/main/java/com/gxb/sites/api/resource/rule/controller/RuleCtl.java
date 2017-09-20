package com.gxb.sites.api.resource.rule.controller;

import com.gxb.sites.api.resource.rule.service.RuleService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by He on 2016/11/4.
 */
@Controller
public class RuleCtl {

    @Autowired
    private RuleService ruleService;
    @RequestMapping(value = "/rule/getRuleByPersonalitytype", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map<String,Object> getRuleByPersonalitytype(int personalitytype) {
        return ruleService.getRuleByPersonalitytype(personalitytype);
    }
}
