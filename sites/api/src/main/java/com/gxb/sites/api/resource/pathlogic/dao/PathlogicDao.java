package com.gxb.sites.api.resource.pathlogic.dao;
import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.pathlogic.Pathlogic;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-10-14.
 */
@LcmsRepository
public interface PathlogicDao extends BasicDao<Pathlogic>{

    int findALLExaminations(@Param(value = "cource") int cource, @Param(value = "personalitytype") int personalitytype);
    int findCourceId(@Param(value = "lock") int lock, @Param(value = "personalitytype") int personalitytype);
    List<Pathlogic> getPathLogicByPtype(String unionid);

}
