package com.gxb.sites.api.resource.combinedaccount.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.user.CombinedAccount;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-10-14.
 */
@LcmsRepository
public interface CombinedAccountDao extends BasicDao<CombinedAccount> {
    CombinedAccount getByOpenIdAndUnionId(@Param(value = "unionId")String unionId);

    List<CombinedAccount> getByLoginKeyOrUnionId(CombinedAccount combinedAccount);

    CombinedAccount getByUserId(Long userId);
}
