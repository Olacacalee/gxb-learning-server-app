package com.gxb.sites.api.resource.fixorder.app.controller;

import com.gxb.modules.domain.fixorder.FixOrder;
import com.gxb.sites.api.resource.fixorder.service.FixOrderService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xing on 2017/9/21.
 */
@Controller
public class FixOrderCtl {

    @Autowired
    private FixOrderService fixOrderService;

    @RequestMapping(value = "/fixorder", method = RequestMethod.POST)
    @ResponseBody
    @AccessTokenCheck(false)
    public Long saveFixOrder(@RequestBody FixOrder fixOrder){
        fixOrder.setFixStatus("0");
        return fixOrderService.saveFixOrder(fixOrder);
    }

}
