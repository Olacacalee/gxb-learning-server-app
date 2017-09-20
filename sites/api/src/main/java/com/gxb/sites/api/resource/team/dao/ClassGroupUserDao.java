package com.gxb.sites.api.resource.team.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.team.ClassGroupUser;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-10-16.
 */
@LcmsRepository
public interface ClassGroupUserDao extends BasicDao<ClassGroupUser> {
    List<ClassGroupUser> listByNoAndTenantId(@Param(value = "no") String no, @Param(value = "tenantId") Long tenantId);

    void batchUpdateList(List<ClassGroupUser> cgus);
}
