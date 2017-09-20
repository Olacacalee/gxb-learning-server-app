package com.gxb.sites.api.resource.feedback.service;

import com.gxb.modules.domain.other.ElectiveFeedBack;
import com.gxb.modules.utils.StringTools;
import com.gxb.sites.api.resource.feedback.dao.ElectiveFeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-20.
 */
@Service
public class ElectiveFeedbackService {

    @Autowired
    private ElectiveFeedbackDao electiveFeedbackDao;

    public Map saveFeedback(String unionId,ElectiveFeedBack feedBack) {
        Map<String,Object> result = new HashMap<>();
        if(StringTools.isBlank(unionId)){
            result.put("status","1");
            result.put("message","openId不能为空");
            return result;
        }
        if(StringTools.isBlank(feedBack.getText())){
            result.put("status","1");
            result.put("message","内容不能为空");
            return result;
        }
        feedBack.setUnionId(unionId);
        electiveFeedbackDao.save(feedBack);
        result.put("status","0");
        return result;
    }
}
