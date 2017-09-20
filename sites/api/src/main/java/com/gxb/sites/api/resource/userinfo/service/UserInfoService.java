package com.gxb.sites.api.resource.userinfo.service;

import com.gxb.modules.cache.service.CacheService;
import com.gxb.modules.domain.cource.Cource;
import com.gxb.modules.domain.electiveuesrinfo.ElectiveUserInfo;
import com.gxb.modules.domain.pathlogic.Pathlogic;
import com.gxb.modules.domain.rule.Rule;
import com.gxb.modules.domain.user.User;
import com.gxb.modules.domain.userschedule.UserSchedule;
import com.gxb.modules.utils.HttpClientTools;
import com.gxb.modules.utils.StringTools;
import com.gxb.sites.api.config.MobileValidateCodeConfig;
import com.gxb.sites.api.config.constant.TransactionConstant;
import com.gxb.sites.api.resource.cource.dao.CourceDao;
import com.gxb.sites.api.resource.pathlogic.dao.PathlogicDao;
import com.gxb.sites.api.resource.rule.dao.RuleDao;
import com.gxb.sites.api.resource.user.dao.UserDao;
import com.gxb.sites.api.resource.userinfo.dao.UserInfoDao;
import com.gxb.sites.api.resource.userschedule.dao.UserScheduleDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-14.
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userinfoDao;
    @Autowired
    private CourceDao courceDao;
    @Autowired
    private RuleDao ruleDao;
    @Autowired
    private UserDao userDao;


    @Autowired
    private PathlogicDao pathlogicDao;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private MobileValidateCodeConfig mobileValidateCodeConfig;
    @Autowired
    private UserScheduleDao userScheduleDao;


    public Map<String,Object> getUserInfoByUserid(String unionid){
        Map<String, Object> map = new HashedMap();
        List<Integer> pathid = new ArrayList<Integer>();
        Integer[] testcourceid = {2,5,8,15,17};//测试章节的courceid

            ElectiveUserInfo user = userinfoDao.getUserInfoByUserUnionID(unionid);
            map.put("data", user);
            List<Pathlogic> path = pathlogicDao.getPathLogicByPtype(unionid);
            pathid.add(0,40);
            for (int i = 0; i < path.size(); i++) {
                pathid.add(i+1, path.get(i).getCourceid());
            }
            map.put("path", pathid);
            map.put("testcourceid",testcourceid);
       return map;
}


    @Transactional(TransactionConstant.LCMS)
    public Map<String,Object> applyScore(Long userId) {
        Map<String,Object> result = new HashedMap();
        UserSchedule userSchedule = userScheduleDao.checkLastChapter(userId);
        if(userSchedule==null){
            result.put("status","1");
            result.put("message","请先学完所有的章节");
            return result;
        }
        ElectiveUserInfo userinfo = userinfoDao.getUserInfoByUserId(StringTools.getInteger(StringTools.getString(userId)));
        if(userinfo==null){
            result.put("status","1");
            result.put("message","请先学完所有的章节");
            return result;
        }
        if(1==userinfo.getExchangecredits()){
            result.put("status","1");
            result.put("message","你已申请过学分");
            return result;
        }
        userinfoDao.applyScore(userId);
        result.put("status",0);
        return result;
    }

    public Map<String,Object> wxLogin(String code){
        String url="https://bigbang.gaoxiaobang.com/wx-qa/getUserInfo.htm?code="+code;
        Map<String,Object> map=new HashedMap();
        map.put("data",HttpClientTools.get(url));
        return map;
    }

    public String getSign(String url){
        String getUrl = "https://bigbang.gaoxiaobang.com/wx-qa/getWechatSign.htm?url="+url;
        return HttpClientTools.get(getUrl);
    }

    public String getRequestCodeUrl(String url){
        String getUrl = "https://bigbang.gaoxiaobang.com/wx-qa/getRequestCodeUrl.htm?url="+url;
        return HttpClientTools.get(getUrl);
    }

    @Transactional(TransactionConstant.LCMS)
    public int updateUserLock(int lock,String unionid) {
        //  +
        lock = lock + 1;
        if(userinfoDao.getUserLockByUnionID(unionid)>=lock){
            return 0;
        }
        Long id = userinfoDao.getUserIDInfoByUserUnionID(unionid);//获取用户的user_id

        int personalitytype = userinfoDao.getUserPersonalityTypeByUnionID(unionid);
        if (personalitytype==0 && lock>=3) {
            return 0;
        }
            int courceid = pathlogicDao.findCourceId(lock, personalitytype);
             if (courceid != 3) {//插入用户进度表下一章解锁的章节内的信息（不是性格你我他的情况）
            List<Cource> courcelist = courceDao.getCourceByParentId(courceid);
                 userScheduleDao.saveUserSchedule(id, courceid, 0, unionid);
            for (int i = 0; i < courcelist.size(); i++) {
                userScheduleDao.saveUserSchedule(id, courcelist.get(i).getId(), 0, unionid);
            }
            } else if (courceid == 3) {//插入用户进度表下一章解锁的章节内的信息（是性格你我他的情况，因为对应着用户人格）
            Cource cource = courceDao.getCourceByPidType(courceid, personalitytype);
            userScheduleDao.saveUserSchedule(id, 23, 0, unionid);//认识九种性格
            userScheduleDao.saveUserSchedule(id, 24, 0, unionid);//神奇的九种性格
            userScheduleDao.saveUserSchedule(id, courceid, 0, unionid);//对应用户的性格解析
            userScheduleDao.saveUserSchedule(id, 46, 0, unionid);//对应用户的性格解析
            userScheduleDao.saveUserSchedule(id,24+personalitytype,0,unionid);//用户对应的人格解析
             }
        if (userinfoDao.getIDInfoByUserUnionID(unionid) == 0) {
             return userinfoDao.saveUserUnionid(unionid);
        } else {
            return userinfoDao.updateUserLockByUnionID(lock, unionid);
        }
    }

    public Map<String,Object> getReport(long userId) {
        Map<String,Object> result = new HashedMap();
        UserSchedule userSchedule = userScheduleDao.checkLastChapter(userId);
        if(userSchedule==null){
            result.put("status","1");
            result.put("message","请先学完所有的章节");
            return result;
        }
        ElectiveUserInfo userinfo = userinfoDao.getUserInfoByUserId(StringTools.getInteger(StringTools.getString(userId)));
        if(userinfo==null){
            result.put("status","1");
            result.put("message","请先学完所有的章节");
            return result;
        }
        result.put("data",userinfo);
        result.put("status",0);
        return result;
    }

    @Transactional(TransactionConstant.LCMS)
    public Map<String,Object> checkReport(Long userId) {
        Map<String,Object> result = new HashedMap();
        UserSchedule userSchedule = userScheduleDao.checkLastChapter(userId);
        if(userSchedule==null){
            result.put("status","1");
            result.put("message","请先学完所有的章节");
            return result;
        }
        ElectiveUserInfo userinfo = userinfoDao.getUserInfoByUserId(StringTools.getInteger(StringTools.getString(userId)));
        if(userinfo==null){
            result.put("status","1");
            result.put("message","请先学完所有的章节");
            return result;
        }
        if(userinfo.getReportTime()==null){
            Date date = new Date();
            userinfo.setReportTime(date);
            userinfoDao.updateReportTime(userinfo);
            result.put("time",StringTools.getString(date.getTime()));
        }else{
            result.put("time",StringTools.getString(userinfo.getReportTime().getTime()));
        }
        result.put("status","0");
        result.put("count",userinfo.getId());
        return result;
    }

    public Map getReportById(Long id) {
        Map<String,Object> result = new HashedMap();
        ElectiveUserInfo userinfo = userinfoDao.getById(id);
        if(userinfo==null){
            result.put("status","1");
            result.put("message","没有该报告");
        }
        result.put("data",userinfo);
        User user = userDao.getById(userinfo.getUserId());
        String name = user.getName()!=null?user.getName():user.getUsername();
        result.put("name",name);
        result.put("status","0");
        return result;
    }

    public Map<String,Object> getUserInfoByUnionid(String unionid) {
        Map<String,Object> map = new HashedMap();
        ElectiveUserInfo userinfo = userinfoDao.getUserInfoByUserUnionID(unionid);
        int personalitytype = userinfo.getPersonalitytype();
        int courceid = 2;   //九型人格
        Rule rule = ruleDao.getUserTestResult(personalitytype, courceid);
        map.put("personalitytype", personalitytype);
        map.put("personalitydesc", rule.getDescription());
        map.put("personalitycharacteristics", rule.getResult());
        return map;
    }
}
