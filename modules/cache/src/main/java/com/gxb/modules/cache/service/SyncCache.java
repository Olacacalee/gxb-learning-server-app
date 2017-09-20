package com.gxb.modules.cache.service;

import com.gxb.modules.contants.CacheKeyStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ${sunninghai} on 16-1-27.
 */
@Component
public class SyncCache {
    @Autowired
    public CacheService cacheService;
    private static Logger LOGGER = LoggerFactory.getLogger(SyncCache.class);

    public  void removeClassesAssignment(Long classId){
        //清空作业缓存
        try{
            String cacheKey = CacheKeyService.getClassesAssignmentKey(classId);
            cacheService.remove(cacheKey);
        }catch (Exception e){
            LOGGER.error("removeClassesAssignment erro:",e);
        }

    }

    public  void removeClassesChapter(Long chapterId){
        //清空chapter缓存
        try{
            String cacheKey = CacheKeyService.getClassesChapterKey(chapterId);
            cacheService.remove(cacheKey);
        }catch (Exception e){
            LOGGER.error("removeClassesChapter erro:",e);
        }

    }

    public  void removeClassesUnit(Long classId){
        //清空章节缓存
        try{
            String cacheKey = CacheKeyService.getClassesUnitsKey(classId);
            cacheService.remove(cacheKey);
        }catch (Exception e){
            LOGGER.error("removeClassesUnit erro:",e);
        }

    }

    public  void removeClassesQuizzes(Long classId){
        //清空章节缓存
        try{
            String cacheKey = CacheKeyService.getClassesQuizzesKey(classId);
            cacheService.remove(cacheKey);
        }catch (Exception e){
            LOGGER.error("removeClassesQuizzes erro:",e);
        }

    }

    public  void removeTenantClass(Long tenantId){
        //清空章节缓存
        try{
            String pattern = CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(tenantId.toString()).concat("*ClassesLcmsCtl/getByTenantId");
            cacheService.removePattern(pattern);
        }catch (Exception e){
            LOGGER.error("removeTenantClass erro:",e);
        }

    }
}
