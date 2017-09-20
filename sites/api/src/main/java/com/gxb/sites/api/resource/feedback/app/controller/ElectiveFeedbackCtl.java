package com.gxb.sites.api.resource.feedback.app.controller;

import com.gxb.modules.domain.other.ElectiveFeedBack;
import com.gxb.sites.api.resource.feedback.service.ElectiveFeedbackService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016-10-20.
 */
@Controller
public class ElectiveFeedbackCtl {
    @Autowired
    private ElectiveFeedbackService electiveFeedbackService;

    @RequestMapping(value = "/feedback/submit", method = RequestMethod.POST)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map saveFeedback(@RequestBody ElectiveFeedBack feedBack,String unionId){
        return electiveFeedbackService.saveFeedback(unionId,feedBack);
    }
}
