package com.gxb.sites.api.resource.student.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.student.Student;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-10-16.
 */
@LcmsRepository
public interface StudentDao extends BasicDao<Student> {
    List<Student> getByUserIdTo(@Param("userId") Long userId);

    Integer countByUserIdAndTenantId(Long userId, Long tenantId);

    Student getBindingInfo(long userId);
}
