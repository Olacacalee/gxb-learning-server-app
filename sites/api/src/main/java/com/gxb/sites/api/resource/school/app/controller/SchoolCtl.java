package com.gxb.sites.api.resource.school.app.controller;

import com.gxb.modules.domain.school.School;
import com.gxb.sites.api.resource.school.service.SchoolService;
import com.gxb.sites.api.sys.auth.AccessTokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016-10-16.
 */
@Controller
public class SchoolCtl {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/school/getSchoolList", method = RequestMethod.GET)
    @ResponseBody
    @AccessTokenCheck(false)
    public List<School> getAppSchoolList(){
        return schoolService.getAppSchoolList();
    }

}
