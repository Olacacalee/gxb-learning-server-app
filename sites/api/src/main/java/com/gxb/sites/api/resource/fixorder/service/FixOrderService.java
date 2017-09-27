package com.gxb.sites.api.resource.fixorder.service;

import com.gxb.modules.core.domain.FilterDomain;
import com.gxb.modules.domain.fixorder.FixOrder;
import com.gxb.modules.domain.result.ResultObject;
import com.gxb.sites.api.resource.fixorder.dao.FixOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xing on 2017/9/21.
 */
@Service
public class FixOrderService {

    @Autowired
    private FixOrderDao fixOrderDao;

    public ResultObject saveFixOrder(FixOrder fixOrder){
        fixOrder.setFixStatus("0");
        fixOrderDao.save(fixOrder);
        return new ResultObject("1","保存成功",fixOrder.getFixOrderId());
    }

    public FilterDomain<FixOrder> getAllFixOrder(FilterDomain<FixOrder> filter){
        List<FixOrder> fixOrderList = fixOrderDao.getAllFixOrder(filter);
        filter.setDataList(fixOrderList);
        return filter;
    }

    public ResultObject getByOrderId(Long fixOrderId){
        return new ResultObject("1","保存成功",fixOrderDao.getByOrderId(fixOrderId));
    }

    public ResultObject updateFixOrder(FixOrder fixOrder){
        fixOrderDao.update(fixOrder);
        return new ResultObject("1","更新成功",null);
    }

    public ResultObject deleteFixOrder(Long fixOrderId){
        fixOrderDao.delete(fixOrderId);
        return new ResultObject("1","删除成功",null);
    }

}
