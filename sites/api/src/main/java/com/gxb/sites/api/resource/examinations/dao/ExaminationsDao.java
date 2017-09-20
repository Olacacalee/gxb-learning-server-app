package com.gxb.sites.api.resource.examinations.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.examinations.Examinations;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by He on 2016/10/16.
 */
@LcmsRepository
public interface ExaminationsDao extends BasicDao<Examinations>{

    List findALLExaminations(int courceid);

    List<Examinations> findALLRightAnwsers(int courceid);

    int getSortByid(int id);

    Map<String, Integer> findThePersonality(List list);

    List<Examinations> findAllIdAndType(@Param(value = "courceid")int courceid, @Param(value = "personality")int personality);
}
