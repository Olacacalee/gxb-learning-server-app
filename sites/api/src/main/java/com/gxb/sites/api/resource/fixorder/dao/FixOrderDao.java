package com.gxb.sites.api.resource.fixorder.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.core.domain.FilterDomain;
import com.gxb.modules.domain.fixorder.FixOrder;
import com.gxb.sites.api.annotation.LcmsRepository;

import java.util.List;

/**
 * Created by cayley on 2017/9/21.
 */
@LcmsRepository
public interface FixOrderDao extends BasicDao<FixOrder>{

    List<FixOrder> getAllFixOrder(FilterDomain<FixOrder> filter);

    FixOrder getByOrderId(Long fixOrderId);

}
