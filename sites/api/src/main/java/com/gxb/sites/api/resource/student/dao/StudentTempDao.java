package com.gxb.sites.api.resource.student.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.student.StudentTemp;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016-10-16.
 */
@LcmsRepository
public interface StudentTempDao extends BasicDao<StudentTemp> {

    StudentTemp getByNoAndTenantIdAndName(@Param("no") String no, @Param("tenantId") Long tenantId, @Param("name")String name);
}
