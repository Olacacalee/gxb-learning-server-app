package com.gxb.sites.api.resource.rule.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.rule.Rule;
import com.gxb.sites.api.annotation.LcmsRepository;
import org.apache.ibatis.annotations.Param;

/**
 * Created by He on 2016/10/19.
 */
@LcmsRepository
public interface RuleDao extends BasicDao<Rule>{

    Rule getUserTestResult(@Param(value = "score") int score, @Param(value = "courceid") long courceid);

    Rule getUserSelfConsciousness(@Param(value = "courceid") int courceid, @Param(value = "description")String description);
}
