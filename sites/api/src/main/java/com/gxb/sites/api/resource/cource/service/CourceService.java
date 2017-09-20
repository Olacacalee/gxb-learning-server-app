package com.gxb.sites.api.resource.cource.service;

import com.gxb.modules.cache.service.CacheKeyService;
import com.gxb.modules.cache.service.CacheService;
import com.gxb.modules.domain.cource.Cource;
import com.gxb.modules.domain.user.User;
import com.gxb.modules.utils.StringTools;
import com.gxb.sites.api.config.MobileValidateCodeConfig;
import com.gxb.sites.api.resource.cource.dao.CourceDao;
import com.gxb.sites.api.resource.userinfo.dao.UserInfoDao;
import com.sun.net.httpserver.Authenticator;
import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Administrator on 2016-10-14.
 */
@Service
public class CourceService {

    @Autowired
    private CourceDao courcedao;

    @Autowired
    private UserInfoDao userinfoDao;

    @Autowired
    private CacheService cacheService;
    @Autowired
    private MobileValidateCodeConfig mobileValidateCodeConfig;


    public Map<String, Object> getCourceByparentId(String unionid, int id) {
        Map<String, Object> map = new HashedMap();
            if (id == -1) {
                map.put("message", "此章节可以跳过");
                map.put("statues", "2");
                return map;
            } else {
                List<Cource> list = courcedao.getCourceByUnionId(unionid, id);
                Cource cource = courcedao.getCourceById(id);
//            if (list.size() > 0 && !cource.equals("null")) {
                map.put("data", list);
                map.put("cource", cource);
                map.put("message", "success");
                map.put("statues", "0");
                return map;
            }
        }

}



