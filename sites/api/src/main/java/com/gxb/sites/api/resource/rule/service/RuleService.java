package com.gxb.sites.api.resource.rule.service;

import com.gxb.modules.domain.rule.Rule;
import com.gxb.sites.api.resource.rule.dao.RuleDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by He on 2016/11/4.
 */
@Service
public class RuleService {

    @Autowired
    private RuleDao ruleDao;
    public Map<String,Object> getRuleByPersonalitytype(int personalitytype) {
        Map<String, Object> map = new HashedMap();
        int courceid = 2;   //九型人格
        Rule rule = ruleDao.getUserTestResult(personalitytype, courceid);
        map.put("personalitytype", personalitytype);
        map.put("personalitydesc", rule.getDescription());
        map.put("personalitycharacteristics", rule.getResult());
        return map;
    }
}
