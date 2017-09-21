package com.gxb.sites.api.resource.fixorder.service;

import com.gxb.modules.domain.fixorder.FixOrder;
import com.gxb.sites.api.config.constant.TransactionConstant;
import com.gxb.sites.api.resource.fixorder.dao.FixOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xing on 2017/9/21.
 */
@Service
public class FixOrderService {

    @Autowired
    private FixOrderDao fixOrderDao;

    public Long saveFixOrder(FixOrder fixOrder){
        fixOrderDao.save(fixOrder);
        return fixOrder.getFixOrderId();
    }

}
