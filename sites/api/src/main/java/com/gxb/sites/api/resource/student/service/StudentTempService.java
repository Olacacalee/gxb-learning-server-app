package com.gxb.sites.api.resource.student.service;

import com.gxb.modules.domain.student.StudentTemp;
import com.gxb.modules.utils.StringTools;
import com.gxb.sites.api.resource.student.dao.StudentTempDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-10-16.
 */
@Service
public class StudentTempService {

    @Autowired
    private StudentTempDao studentTempDao;

    public StudentTemp getByNoAndTenantIdAndName(String no, Long tenantId, String name){
        if(StringTools.isNotBlank(no)){
            no = no.toLowerCase();
        }
        return studentTempDao.getByNoAndTenantIdAndName(no, tenantId,name);
    }
}
