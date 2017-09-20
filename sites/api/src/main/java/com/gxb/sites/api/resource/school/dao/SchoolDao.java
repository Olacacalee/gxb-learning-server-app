package com.gxb.sites.api.resource.school.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.school.School;
import com.gxb.sites.api.annotation.LcmsRepository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-16.
 */
@LcmsRepository
public interface SchoolDao extends BasicDao<School>{

    List<School> getAppSchoolList();
}
