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

    /**
     * 保存维修工单
     * @param fixOrder
     * @return
     */
    @RequestMapping(value = "/fixorder", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject saveFixOrder(@RequestBody FixOrder fixOrder){
        return fixOrderService.saveFixOrder(fixOrder);
    }

    /**
     * 维修工单列表
     * @param filterDomain
     * @return
     */
    @RequestMapping(value = "/fixorder/list", method = RequestMethod.GET)
    @ResponseBody
    public FilterDomain<FixOrder> getAllFixOrder(FilterDomain<FixOrder> filterDomain){
        return fixOrderService.getAllFixOrder(filterDomain);
    }

    /**
     * 获取工单详情
     * @param fixOrderId
     * @return
     */
    @RequestMapping(value = "/fixorder/fixOrderId/{fixOrderId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject getByOrderId(@PathVariable Long fixOrderId){
        return fixOrderService.getByOrderId(fixOrderId);
    }

    /**
     * 更新维修工单
     * @param fixOrder
     * @return
     */
    @RequestMapping(value = "/fixorder", method = RequestMethod.PUT)
    @ResponseBody
    public ResultObject updateFixOrder(@RequestBody FixOrder fixOrder){
        return fixOrderService.updateFixOrder(fixOrder);
    }

    /**
     * 删除维修工单
     * @param fixOrderId
     * @return
     */
    @RequestMapping(value = "/fixorder/fixOrderId/{fixOrderId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultObject deleteFixOrder(@PathVariable Long fixOrderId){
        return fixOrderService.deleteFixOrder(fixOrderId);
    }

}
