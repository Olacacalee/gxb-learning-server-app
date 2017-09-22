package com.gxb.sites.api.resource.fixorder.app.controller;

import com.gxb.modules.core.domain.FilterDomain;
import com.gxb.modules.domain.fixorder.FixOrder;
import com.gxb.modules.domain.result.ResultObject;
import com.gxb.sites.api.resource.fixorder.service.FixOrderService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xing on 2017/9/21.
 */
@Controller
public class FixOrderCtl {

    @Autowired
    private FixOrderService fixOrderService;

    @RequestMapping(value = "/fixorder", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject saveFixOrder(@RequestBody FixOrder fixOrder){
        return fixOrderService.saveFixOrder(fixOrder);
    }

    @RequestMapping(value = "/fixorder/list", method = RequestMethod.GET)
    @ResponseBody
    public FilterDomain<FixOrder> getAllFixOrder(FilterDomain<FixOrder> filterDomain){
        return fixOrderService.getAllFixOrder(filterDomain);
    }

    @RequestMapping(value = "/fixorder/fixOrderId/{fixOrderId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject getByOrderId(@PathVariable Long fixOrderId){
        return fixOrderService.getByOrderId(fixOrderId);
    }

}
