package com.gxb.sites.api.resource.school.service;

import com.gxb.modules.domain.school.School;
import com.gxb.sites.api.resource.school.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-10-16.
 */
@Service
public class SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    public List<School> getAppSchoolList() {
        return schoolDao.getAppSchoolList();
    }
}
