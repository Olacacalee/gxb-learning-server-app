package com.gxb.sites.api.resource.examinations.controller;

import com.gxb.sites.api.resource.examinations.service.ExaminationsService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by He on 2016/10/16.
 */
@Controller
public class ExaminationsCtl {

    @Autowired
    private ExaminationsService examinationsService;

    @RequestMapping(value = "/examinations/findALLExaminations", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map <String, Object> findALLExaminations(int cource, String unionid){
        return examinationsService.findALLExaminations(cource, unionid);
    }

    @RequestMapping(value = "/examinations/analysisTheAnswer", method = RequestMethod.POST)
    @ResponseBody
    @AccessTokenCheck(false)
    public Map AnalysisTheAnswer(int courceid, String unionid, @RequestBody List<Map<Integer,String>> userAnswer){
        return examinationsService.AnalysisTheAnswer(courceid, unionid, userAnswer);
    }

}
