package com.gxb.sites.api.resource.userschedule.service;

import com.gxb.modules.cache.service.CacheService;
import com.gxb.modules.domain.electiveuesrinfo.ElectiveUserInfo;
import com.gxb.modules.domain.pathlogic.Pathlogic;
import com.gxb.sites.api.config.MobileValidateCodeConfig;
import com.gxb.sites.api.config.constant.TransactionConstant;
import com.gxb.sites.api.resource.pathlogic.dao.PathlogicDao;
import com.gxb.sites.api.resource.userinfo.dao.UserInfoDao;
import com.gxb.sites.api.resource.userschedule.dao.UserScheduleDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-14.
 */
@Service
public class UserScheduleService {

    @Autowired
    private UserScheduleDao userscheduleDao;

    @Autowired
    private UserInfoDao userinfoDao;

    @Autowired
    private CacheService cacheService;
    @Autowired
    private MobileValidateCodeConfig mobileValidateCodeConfig;

    @Transactional(TransactionConstant.LCMS)
    public int updateSchedule(String unionid,int schedule,int courceid){
        Long userid=userinfoDao.getUserIDInfoByUserUnionID(unionid);
        int id=userscheduleDao.scheduleIsExist(unionid,courceid);
        if(id==0){
           return userscheduleDao.saveUserSchedule(courceid,userid,schedule,unionid);
        }else if(id!=0){
            return userscheduleDao.updateUserSchedule(id,schedule);
        }
        return 0;
    };






}
