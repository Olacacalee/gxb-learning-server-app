package com.gxb.modules.cache.service;

import com.gxb.modules.contants.CacheKeyStatic;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jqwang on 15/10/27.
 */
public class CacheKeyService {

    /**
     * 班次下的章节集合缓存用到的key
     *
     * @param classesId
     * @return
     */
    public static String getClassesUnitsKey(@NotBlank Long classesId) {
        return CacheKeyStatic.CLASS.concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(classesId.toString()).concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(CacheKeyStatic.UNITS);
    }

    public static String getClassesChapterKey(@NotBlank Long chapterId) {

        return CacheKeyStatic.CLASS.concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(CacheKeyStatic.UNIT).concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(CacheKeyStatic.CHAPTER).concat(chapterId.toString());
    }

    public static String getClassesChapterKey(@NotBlank Long classId, Long contentId, String contentType) {

        return CacheKeyStatic.CLASS.concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(CacheKeyStatic.UNIT).concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(CacheKeyStatic.CHAPTER).concat(CacheKeyStatic.JOIN_SYMBOL).concat(classId.toString()).concat(CacheKeyStatic.JOIN_SYMBOL)
                .concat(contentId.toString()).concat(CacheKeyStatic.JOIN_SYMBOL).concat(contentType);
    }

    public static String getClassesQuizzesKey(@NotBlank Long classId) {

        return CacheKeyStatic.CLASS.
                concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(classId.toString()).
                concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(CacheKeyStatic.QUIZZES);
    }

    public static String getClassesAssignmentKey(@NotBlank Long classId) {
        return CacheKeyStatic.CLASS.concat(classId.toString()).concat(CacheKeyStatic.ASSIGNMENT);
    }

    public static String getUserLearnRecordKey(@NotBlank Long chapterId, @NotBlank Long userId) {
        return CacheKeyStatic.LEARN_RECORD.
                concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(chapterId.toString()).concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(userId.toString());
    }

    public static String getUserLearnScheduleKey(@NotBlank Long chapterId, @NotBlank Long userId) {
        return CacheKeyStatic.LEARN_SCHEDULE.concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(chapterId.toString()).concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(userId.toString());

    }

    public static String getLearnLogKey(@NotBlank Long classId, @NotBlank Long userId) {
        return CacheKeyStatic.LEARN_LOG.concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(classId.toString()).concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(userId.toString());
    }

    public static String getSchoolIdCacheKey(Long schoolId) {
        return CacheKeyStatic.SCHOOL.concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(schoolId.toString());
    }

	public static String getSchoolShortnameCacheKey(String shortname) {
		return CacheKeyStatic.SCHOOL_SHORTNAME.concat(CacheKeyStatic.JOIN_SYMBOL).concat(shortname);
	}

    public static String getUserCacheKey(Long userId) {
        return CacheKeyStatic.USER.concat(CacheKeyStatic.JOIN_SYMBOL).
                concat(userId.toString());
    }

    ///获取web站点用于缓存用户会话的CacheKey
    public static String getWebClientUserTokenCacheKey( String sessionId) {
        return CacheKeyStatic.WEB_CLIENT_USER_SESSION_PREFIX.
                concat(CacheKeyStatic.JOIN_SYMBOL).concat(sessionId);
    }

    ///获取web站点用于缓存用户会话的CacheKey
    public static String getWebClientUserSchoolCacheKey( String sessionId) {
        return CacheKeyStatic.WEB_CLIENT_USER_SCHOOL_SESSION_PREFIX.
                concat(CacheKeyStatic.JOIN_SYMBOL).concat(sessionId);
    }

    //获取手机注册时验证码
    public static String getRegisterMobileCode(String mobile) {
        return CacheKeyStatic.REGISTER.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.MOBILE).concat(CacheKeyStatic.JOIN_SYMBOL).concat(mobile);
    }

    //获取邮箱注册时验证码
    public static String getRegisterEmailCode(String email) {
        return CacheKeyStatic.REGISTER.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.EMAIL).concat(CacheKeyStatic.JOIN_SYMBOL).concat(email);
    }

    //获取待激活邮件
    public static String getActivationEmail(String code) {
        return CacheKeyStatic.REGISTER.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.ACTIVATION).concat(CacheKeyStatic.JOIN_SYMBOL).concat(code);
    }

    //获取修改邮箱邮件
    public static String getChangeEmail(String token) {
        return CacheKeyStatic.CHANGE.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.EMAIL).concat(CacheKeyStatic.JOIN_SYMBOL).concat(token);
    }

    //获取重置密码
    public static String getResetPassword(String key) {
        return CacheKeyStatic.RESET.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.PASSWORD).concat(CacheKeyStatic.JOIN_SYMBOL).concat(key);
    }

    //获取重置密码session
    public static String getResetPasswordSession(String token) {
        return CacheKeyStatic.RESET.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.PASSWORD).concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.SESSION).concat(CacheKeyStatic.JOIN_SYMBOL).concat(token);
    }

    public static String getImportClassGroupUser(String tenantId,String classId){
        return CacheKeyStatic.IMPORT.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.CLASSGROUPUSER).concat(CacheKeyStatic.JOIN_SYMBOL).concat(tenantId).concat(CacheKeyStatic.JOIN_SYMBOL).concat(classId);
    }

    public static String getImportStudent(String tenantId){
        return CacheKeyStatic.IMPORT.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.STUDENT).concat(CacheKeyStatic.JOIN_SYMBOL).concat(tenantId);
    }

    public static String getAppResetPasswordMobileCode(String mobile) {
        return CacheKeyStatic.APP.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.RESET).concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.PASSWORD).concat(CacheKeyStatic.JOIN_SYMBOL).concat(mobile);
    }

    public static String getTenantKey(@NotBlank Long tenantId) {
        return CacheKeyStatic.TENANT.concat(CacheKeyStatic.JOIN_SYMBOL).concat(tenantId.toString());
    }


    public static String getClassCategoryLcmsCtlGetByTenantId(@NotBlank String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat( tenantId + ":ClassCategoryLcmsCtl/getByTenantId");
    }


    public static String getClassesLcmsCtlGetClassesById(@NotBlank String classId,String userId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat( classId + ":userId"+userId+":ClassesLcmsCtl/getClassesById" );
    }


    public static String getClassesLcmsCtlGetUnits(@NotBlank String classId,String userId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat( classId + ":"+userId+":ClassesLcmsCtl/getUnits" );
    }

    public static String getClassesLcmsCtlGetTeachListByClass(@NotBlank String classId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(  classId + ":ClassesLcmsCtl/getTeachListByClass" );
    }

    public static String getClassesLcmsCtlGetTeachListByClass( String tenantId, String curPage ,String pageSize,String sort) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(   tenantId + "/" + curPage.toString() + "/" + pageSize.toString() + "/" + sort + ":ClassesLcmsCtl/getByTenantId" );
    }

    public static String getClassesLcmsCtlGetByCategoryId( String categoryId,String tenantId, String curPage ,String pageSize,String sort) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(   categoryId + "/" +tenantId+"/"+ curPage.toString() + "/" + pageSize.toString() + "/" + sort + ":ClassesLcmsCtl/getByCategoryId" );
    }

    public static String getTenantLcmsCtlGeteRcommendById( String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(  tenantId + ":TenantLcmsCtl/geteRcommendById" );
    }

    public static String getTenantLcmsCtlGetClassesRelatedByTenantId( String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(  tenantId + "TenantLcmsCtl/getClassesRelatedByTenantId");
    }


    public static String getTenantLcmsCtlGetClassesHezuoByTenantId( String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(  tenantId + "TenantLcmsCtl/getClassesHezuoByTenantId");
    }

    public static String getTenantLcmsCtlGetClassesGuessyoulikeByTenantId( String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(  tenantId + "TenantLcmsCtl/getClassesGuessyoulikeByTenantId");
    }
    public static String getTenantLcmsCtlGetJCSTClassByTenantId( String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(  tenantId + "TenantLcmsCtl/getJCSTClassByTenantId");
    }
    public static String getTenantLcmsCtlGetTenant( String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat(  tenantId + "TenantLcmsCtl/getTenant");
    }
    public static String getTenantLcmsCtlGetBannerByTenantId( String tenantId) {
        return CacheKeyStatic.WEB.concat(CacheKeyStatic.JOIN_SYMBOL).concat( tenantId + "TenantLcmsCtl/getBannerByTenantId");
    }
    public static String getUserFunction( String userId) {
        return CacheKeyStatic.USER.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.FUNCTION).concat(CacheKeyStatic.JOIN_SYMBOL).concat(userId);
    }

    public static String checkCanRegisterOrNot(String mobile) {
        return CacheKeyStatic.REGISTER.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.MOBILE).concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.CAN_REGISTER).concat(mobile);
    }

    public static String getRollCallCountKey(Long rollCallId) {
        return CacheKeyStatic.ROLL_CALL.concat(CacheKeyStatic.JOIN_SYMBOL).concat(rollCallId.toString()).concat(CacheKeyStatic.ROLL_CALL_SIGN_COUNT);
    }

    public static String getFileUploadUrlKey() {
        return CacheKeyStatic.FILE_UPLOAD.concat(CacheKeyStatic.JOIN_SYMBOL).concat("Map");
    }

    public static String getUserBind(Long userId){
        return CacheKeyStatic.USER.concat(CacheKeyStatic.JOIN_SYMBOL).concat(CacheKeyStatic.BIND).
                concat(CacheKeyStatic.JOIN_SYMBOL).concat(userId.toString());
    }

    public static String getMobileSendLimitKey(String mobile){
        return CacheKeyStatic.MOBILE_SEND_LIMIT.concat(CacheKeyStatic.JOIN_SYMBOL).concat(mobile);
    }
//







}
