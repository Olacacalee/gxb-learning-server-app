package com.gxb.sites.api.resource.pathlogic.service;

import com.gxb.modules.cache.service.CacheService;
import com.gxb.modules.domain.cource.Cource;

import com.gxb.sites.api.config.MobileValidateCodeConfig;

import com.gxb.sites.api.resource.pathlogic.dao.PathlogicDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-14.
 */
@Service
public class PathlogicService {

    @Autowired
    private PathlogicDao pathlogicDao;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private MobileValidateCodeConfig mobileValidateCodeConfig;


}