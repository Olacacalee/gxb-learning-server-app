package com.gxb.sites.api.resource.user.service;

import com.gxb.modules.cache.service.CacheKeyService;
import com.gxb.modules.cache.service.CacheService;
import com.gxb.modules.constants.Constants;
import com.gxb.modules.constants.DomainConstants;
import com.gxb.modules.contants.CacheKeyStatic;
import com.gxb.modules.core.exception.ServiceException;
import com.gxb.modules.domain.auth.AuthToken;
import com.gxb.modules.domain.electiveuesrinfo.ElectiveUserInfo;
import com.gxb.modules.domain.school.Tenant;
import com.gxb.modules.domain.student.Student;
import com.gxb.modules.domain.student.StudentTemp;
import com.gxb.modules.domain.team.ClassGroupUser;
import com.gxb.modules.domain.user.CombinedAccount;
import com.gxb.modules.domain.user.User;
import com.gxb.modules.domain.user.UserProfile;
import com.gxb.modules.domain.useranswer.UserAnswer;
import com.gxb.modules.domain.userschedule.UserSchedule;
import com.gxb.modules.utils.*;
import com.gxb.sites.api.config.MobileValidateCodeConfig;
import com.gxb.sites.api.config.constant.TransactionConstant;
import com.gxb.sites.api.resource.asset.service.AssetService;
import com.gxb.sites.api.resource.combinedaccount.dao.CombinedAccountDao;
import com.gxb.sites.api.resource.combinedaccount.service.CombinedAccountService;
import com.gxb.sites.api.resource.school.dao.TenantDao;
import com.gxb.sites.api.resource.student.dao.StudentDao;
import com.gxb.sites.api.resource.student.dao.StudentTempDao;
import com.gxb.sites.api.resource.student.service.StudentTempService;
import com.gxb.sites.api.resource.team.dao.ClassGroupUserDao;
import com.gxb.sites.api.resource.user.dao.UserDao;
import com.gxb.sites.api.resource.user.dao.UserProfileDao;
import com.gxb.sites.api.resource.useranswer.dao.UserAnswerDao;
import com.gxb.sites.api.resource.userinfo.dao.UserInfoDao;
import com.gxb.sites.api.resource.userinfo.service.UserInfoService;
import com.gxb.sites.api.resource.userschedule.dao.UserScheduleDao;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Administrator on 2016-10-14.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private CombinedAccountDao combinedAccountDao;
    @Autowired
    private MobileValidateCodeConfig mobileValidateCodeConfig;
    @Autowired
    private UserProfileDao userProfileDao;
    @Autowired
    private CombinedAccountService combinedAccountService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentTempService studentTempService;
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private StudentTempDao studentTempDao;
    @Autowired
    private ClassGroupUserDao classGroupUserDao;
    @Autowired
    private UserInfoDao userinfoDao;
    @Autowired
    private UserScheduleDao userScheduleDao;
    @Autowired
    private UserAnswerDao userAnswerDao;
    @Autowired
    private UserInfoService userInfoService;

    private int connectTimeOut = 5000;
    private int readTimeOut = 10000;
    private String requestEncoding = "UTF-8";
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private static String VOICE = "1";

    public Map getMobileCode(String mobile,String type) {
        Map<String,Object> result = new HashMap<>();

        if (!StringTools.isMobile(mobile)) {
            result.put("status","1");
            result.put("message","手机号码不合法");
            return result;
        }

//        if (userDao.getByMobile(mobile)!=null) {
//            result.put("status","1");
//            result.put("message","此手机号已被注册");
//            return result;
//        }

        if (StringTools.isNotBlank(type) && StringTools.equalsString(type, VOICE))
            return sendMobileCode(mobile, CacheKeyService.getRegisterMobileCode(mobile), result, true);
        else
            return sendMobileCode(mobile, CacheKeyService.getRegisterMobileCode(mobile), result, false);
    }

    private Map sendMobileCode(String mobile, String key, Map result, Boolean isVoice) {

        //最后一次请求时间key
        String keyTime = key.concat("_time");

        Long lastSentTime = (Long) cacheService.getObject(keyTime);

        if (lastSentTime != null && System.currentTimeMillis() - lastSentTime <= 60000) {

            result.put("status","1");
            result.put("message","一分钟内不能重复发送");
            return result;//一分钟内不能重复发送

        } else {
            cacheService.putObject(keyTime, System.currentTimeMillis(), 60l);
        }

        //如果不过期,直接返回
        String code = (String) cacheService.getObject(key);

        if (StringTools.isBlank(code) || cacheService.getExpire(key) < 60) {
            code = getMobileRandomCode();
            cacheService.putObject(key, code, 360l);
        }
        String msg = code + "高校邦验证码（5分钟内有效）。【高校邦】";

        int status = sendMobileCode(mobile, code, msg, isVoice);
        result.put("status","0");
        result.put("smsCode",StringTools.getString(status));
        return result;
    }

    public String getMobileRandomCode() {

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    //0:发送成功 1:发送失败 2:一分钟不能重复发送
    public int sendMobileCode(String mobile, String code, String msg, Boolean isVoice) {


        String mobileSendLimitKey = CacheKeyService.getMobileSendLimitKey(mobile);
        Integer mobileSendLimitNum = (Integer) cacheService.getObject(mobileSendLimitKey);

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        long second = (tomorrow.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        if (mobileSendLimitNum != null) {
            mobileSendLimitNum = mobileSendLimitNum + 1;
            if (mobileSendLimitNum > 5) {
                return 3;
            }
            cacheService.putObject(mobileSendLimitKey, mobileSendLimitNum, second);
        } else {
            cacheService.putObject(mobileSendLimitKey, 1, second);
        }

        Map<String, String> map = new HashMap<>();
        map.put("DesNo", mobile);

        String temp;
        if (isVoice) {
            map.put("userCode", mobileValidateCodeConfig.getVoiceUserCode());// 此处填写用户账号
            map.put("userPass", mobileValidateCodeConfig.getVoiceUserPass());// 此处填写用户密码
            map.put("Channel", mobileValidateCodeConfig.getVoiceChannel());// 此处填写模板短信编号
            map.put("VoiceCode", code);
            map.put("Amount", mobileValidateCodeConfig.getVoiceAmount());
            map.put("TemplateID", mobileValidateCodeConfig.getVoiceTemplateID());
            temp = doPost(mobileValidateCodeConfig.getVoiceUrl(), map, "utf-8", true);
        } else {
            map.put("userCode", mobileValidateCodeConfig.getTriggerUserCode());// 此处填写用户账号
            map.put("userPass", mobileValidateCodeConfig.getTriggerUserPass());// 此处填写用户密码
            map.put("Channel", mobileValidateCodeConfig.getTriggerChannel());// 此处填写模板短信编号
            map.put("Msg", msg);// 此处填写模板短信编号
            temp = doPost(mobileValidateCodeConfig.getTriggerUrl(), map, "utf-8", true);
        }
        if (!StringTools.isBlank(temp) && Long.parseLong(temp) > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    private String doPost(String reqUrl, Map<String, String> parameters,
                          String recvEncoding, Boolean needAnalysis) {

        HttpURLConnection url_con = null;
        String responseContent = null;
        String vchartset = recvEncoding == "" ? requestEncoding
                : recvEncoding;
        try {
            StringBuffer params = new StringBuffer();
            for (Iterator<?> iter = parameters.entrySet().iterator(); iter
                    .hasNext(); ) {
                Map.Entry<?, ?> element = (Map.Entry<?, ?>) iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),
                        vchartset));
                params.append("&");
            }

            if (params.length() > 0) {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            url_con.setConnectTimeout(connectTimeOut);
            url_con.setReadTimeout(readTimeOut);
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            byte[] echo = new byte[10 * 1024];
            int len = in.read(echo);
            responseContent = (new String(echo, 0, len)).trim();
            int code = url_con.getResponseCode();
            if (code != 200) {
                responseContent = "ERROR" + code;
            }
            if (needAnalysis) {
                Document doc = null;
                doc = DocumentHelper.parseText(responseContent);
                if (doc != null) {
                    Element rootElt = doc.getRootElement(); // 获取根节点
                    if (rootElt != null)
                        responseContent = rootElt.getText(); // 拿到根节点的名称
                }
            }
        } catch (IOException e) {
            LOG.error("网络故障:" + e.toString());
        } catch (DocumentException e) {
            LOG.error("解析xmlerror:" + e.toString());
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
        }
        return responseContent;
    }

    @Transactional(TransactionConstant.LCMS)
    public Map checkOpenId(String openId, String unionId) {
        Map<String,Object> result = new HashMap<>();
        CombinedAccount ca = combinedAccountDao.getByOpenIdAndUnionId(unionId);
        Long userId = 0l;
        if(ca!=null){
            if(StringTools.isNotBlank(ca.getMobile())){
                cacheService.putObject(Constants.WECHAT_USER_MOBILE+ca.getUserId(),ca.getMobile());
                result.put("mobile",ca.getMobile());

                User user = userDao.getById(ca.getUserId());

                //增加登录状态
                String tokenPrefix = DomainConstants.GXB_WECHAT_TOKEN_FREFIX;
                String uuid = UUID.randomUUID().toString();
                String token = tokenPrefix + uuid;
                AuthToken authToken = new AuthToken();
                authToken.setCreatedAt(new Date());
                authToken.setUserId(ca.getUserId());
                authToken.setToken(token);
                user.setAccessToken(uuid);
                cacheService.putObject(token, authToken,30*24*60*60l);

                Student student = studentDao.getBindingInfo(ca.getUserId());
                if(student!=null){
                    result.put("bindingStatus","1");
                }else{
                    result.put("bindingStatus","0");
                }

                result.put("data",user);
                userId = user.getUserId();
            }
            if(StringTools.isBlank(ca.getOpenId())){
                ca.setOpenId(openId);
                combinedAccountDao.update(ca);
            }
        }else{
            CombinedAccount newCa = new CombinedAccount();
            newCa.setOpenId(openId);
            newCa.setUnionId(unionId);
            newCa.setKeyType("WECHAT");
            newCa.setDeleteFlag(DomainConstants.DELETE_FLAG_AVAILABLE);
            combinedAccountDao.save(newCa);
        }

        ElectiveUserInfo userInfo = userinfoDao.getUserInfoByUserUnionID(unionId);
        if(userInfo==null){
            userInfo = new ElectiveUserInfo();
            userInfo.setUnionid(unionId);
            userInfo.setUserId(userId);
            userInfo.setCreationtime(new Date());
            userinfoDao.saveUserInfoByUnionId(userInfo);
        }

        result.put("status","0");
        result.put("message","success");
        return result;
    }

    @Transactional(TransactionConstant.LCMS)
    public Map register(User userVo) {
        Map<String,Object> result = new HashMap<>();
        CombinedAccount ca = combinedAccountDao.getByOpenIdAndUnionId(userVo.getUnionId());
        if(ca!=null && StringTools.isBlank(StringTools.getString(ca.getUserId()))){
            if(StringTools.isBlank(userVo.getEncryptedPassword())){
                result.put("status","1");
                result.put("message","密码不能为空");
                return result;
            }
            if(!StringTools.isMobile(userVo.getMobile())){
                result.put("status","1");
                result.put("message","手机号码格式不正确");
                return result;
            }
            int count = userDao.countByMobile(userVo.getMobile());
            if (count > 0) {
                result.put("status","1");
                result.put("message","手机号码已被注册");
                return result;
            }
            String key = CacheKeyService.getRegisterMobileCode(userVo.getMobile());
            String code = (String) cacheService.getObject(key);
            //如果验证码不正确直接返回
//            if (StringTools.isBlank(code) || !userVo.getValidateCode().toLowerCase().equals(code.toLowerCase())) {
//                result.put("status","1");
//                result.put("message","验证码不正确");
//                return result;
//            }
            //创建账号
            User user = new User();
            user.setMobile(userVo.getMobile());
            user.setUsername(userVo.getUsername());
            user.setEncryptedPassword(BCrypt.hashpw(userVo.getEncryptedPassword(), BCrypt.gensalt(10)));
            Date now = DateTools.getCurrentDateTime();
            user.setStatus(DomainConstants.USER_ACTIVATED);
            user.setUpdatedAt(now);
            user.setCreatedAt(now);
            user.setConfirmStatus(DomainConstants.USER_NOT_CONFIRM);
            user.setUuid(UUID.randomUUID().toString());
            user.setDeleteFlag(DomainConstants.DELETE_FLAG_AVAILABLE);
            userDao.save(user);
            saveInitUserProfile(now, user.getUserId());

            //保存头像
            String avatarPath = saveUserAvatar(userVo);
            assetService.userResetLink(user.getUserId(), avatarPath);
            user.setAvatarUrl(avatarPath);

            //更新绑定表
            CombinedAccount combinedAccount = new CombinedAccount();
            combinedAccount.setOpenId(userVo.getOpenId());
            combinedAccount.setUnionId(userVo.getUnionId());
            combinedAccount.setKeyType("WECHAT");
            combinedAccount.setDeleteFlag(DomainConstants.DELETE_FLAG_AVAILABLE);
            combinedAccount.setUserId(user.getUserId());
            combinedAccountService.bindCombinedAccountByUnionId(combinedAccount.getLoginKey(),combinedAccount.getKeyType(),user,combinedAccount.getUnionId());

            //增加登录状态
            String tokenPrefix = DomainConstants.GXB_WECHAT_TOKEN_FREFIX;
            String uuid = UUID.randomUUID().toString();
            String token = tokenPrefix + uuid;
            AuthToken authToken = new AuthToken();
            authToken.setCreatedAt(new Date());
            authToken.setUserId(user.getUserId());
            authToken.setToken(token);
            user.setAccessToken(uuid);
            cacheService.putObject(token, authToken,30*24*60*60l);

            //更新user_info 表
            ElectiveUserInfo userInfo = userinfoDao.getUserInfoByUserUnionID(userVo.getUnionId());
            if(userInfo!=null && userInfo.getUserId()==0){
                userinfoDao.updateUserIdByUnionId(user.getUserId(),userVo.getUnionId());
            }
//            //更新user_schedule
            List<UserSchedule> scheduleList = userScheduleDao.getByUnionId(userVo.getUnionId());
            if(scheduleList!=null && scheduleList.size()>0 && scheduleList.get(0).getUserId()==0){
                userScheduleDao.updateUserIdByUnionId(user.getUserId(),userVo.getUnionId());
            }
//            //更新user_answer
            UserAnswer userAnswer = userAnswerDao.getByUnionId(userVo.getUnionId());
            if(userAnswer!=null && userAnswer.getUserId()==0){
                userAnswerDao.updateUserIdByUnionId(user.getUserId(),userVo.getUnionId());
            }

            result.put("status","0");
            result.put("data",user);
        }
        return result;
    }

    private void saveInitUserProfile(Date now, long userId) {
        UserProfile usersProfile = new UserProfile();
        usersProfile.setUserId(userId);
        usersProfile.setFailAttempts(0);
        usersProfile.setCreatedAt(now);
        usersProfile.setUpdatedAt(now);
        userProfileDao.save(usersProfile);
    }

    private String saveUserAvatar(User userVo) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String path = getImageByUrl(userVo.getAvatarUrl(),"/gxb-file/uploads/avatar/link/",uuid+".png");
        System.out.println(path);

        return "/uploads/avatar/link/"+uuid+".png";
    }

    public static String getImageByUrl(String imageurl, String savepath, String name) {
        try {
            // 构造URL
            URL url = new URL(imageurl);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // Map<String, Object> property =
            // getProperties("/gbtags.properties");
            File file = new File(savepath);// (String) property.get("ewmPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 输出的文件流
            OutputStream os = new FileOutputStream(savepath + name);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            return "error";
        }
    }

    @Transactional(TransactionConstant.LCMS)
    public Map checkMobileCode(User user) {
        Map<String,Object> result = new HashMap<>();
        if(StringTools.isBlank(user.getMobile())){
            result.put("status","1");
            result.put("message","手机号不能为空");
            return result;
        }
        if(!StringTools.isMobile(user.getMobile())){
            result.put("status","1");
            result.put("message","手机号格式不正确");
            return result;
        }
        if(StringTools.isBlank(user.getValidateCode())){
            result.put("status","1");
            result.put("message","验证码不能为空");
            return result;
        }
        String key = CacheKeyService.getRegisterMobileCode(user.getMobile());
        String code = (String) cacheService.getObject(key);
        //如果验证码不正确直接返回
        if (StringTools.isBlank(code) || !user.getValidateCode().toLowerCase().equals(code.toLowerCase())) {
            result.put("status","1");
            result.put("message","验证码不正确");
            return result;
        }
        User u = userDao.getByMobile(user.getMobile());
        if(u==null){
            result.put("status","2");
            result.put("message","该账号还未注册，跳到设置密码页");
            return result;
        }

        //更新绑定表
        CombinedAccount combinedAccount = new CombinedAccount();
        combinedAccount.setOpenId(user.getOpenId());
        combinedAccount.setUnionId(user.getUnionId());
        combinedAccount.setKeyType("WECHAT");
        combinedAccount.setDeleteFlag(DomainConstants.DELETE_FLAG_AVAILABLE);
        combinedAccount.setUserId(u.getUserId());
        combinedAccountService.bindCombinedAccountByUnionId(combinedAccount.getLoginKey(),combinedAccount.getKeyType(),u,combinedAccount.getUnionId());


        //增加登录状态
        String tokenPrefix = DomainConstants.GXB_WECHAT_TOKEN_FREFIX;
        String uuid = UUID.randomUUID().toString();
        String token = tokenPrefix + uuid;
        AuthToken authToken = new AuthToken();
        authToken.setCreatedAt(new Date());
        authToken.setUserId(u.getUserId());
        authToken.setToken(token);
        u.setAccessToken(uuid);
        cacheService.putObject(token, authToken,30*24*60*60l);

        //更新user_info 表
        ElectiveUserInfo userInfo = userinfoDao.getUserInfoByUserUnionID(user.getUnionId());
        if(userInfo!=null && userInfo.getUserId()==0){
            userinfoDao.updateUserIdByUnionId(u.getUserId(),user.getUnionId());
        }
//            //更新user_schedule
        List<UserSchedule> scheduleList = userScheduleDao.getByUnionId(user.getUnionId());
        if(scheduleList!=null && scheduleList.size()>0 && scheduleList.get(0).getUserId()==0){
            userScheduleDao.updateUserIdByUnionId(u.getUserId(),user.getUnionId());
        }
//            //更新user_answer
        UserAnswer userAnswer = userAnswerDao.getByUnionId(user.getUnionId());
        if(userAnswer!=null && userAnswer.getUserId()==0){
            userAnswerDao.updateUserIdByUnionId(u.getUserId(),user.getUnionId());
        }

        List<Student> studentList = studentDao.getByUserIdTo(u.getUserId());
        if(studentList==null || studentList.size()==0){
            result.put("status","3");
            result.put("message","该账号还未认证，跳到认证页");
            result.put("data",u);
            return result;
        }

        userInfoService.updateUserLock(2,user.getUnionId());
        result.put("data",u);
        result.put("status","0");
        return result;
    }

    @Transactional(TransactionConstant.LCMS)
    public Map bindingSchool(Long userId, Student student) {
        Map<String,Object> result = new HashMap<>();
        String key = CacheKeyService.getUserBind(userId);
        String value = (String)cacheService.getObject(key);
        if(StringTools.isNotBlank(value)) {
            return null;
        }
        cacheService.putObject(key, CacheKeyStatic.BIND);
        try{
            String no = student.getNo();
            Long tenantId = student.getTenantId();
            String name = student.getName();

            if(StringTools.isBlank(no)){
                result.put("status","1");
                result.put("message","输入的学号有误");
                return result;
            }

            if(tenantId == null){
                result.put("status","1");
                result.put("message","租户id不能为空");
                return result;
            }

            if(StringTools.isBlank(name)){
                result.put("status","1");
                result.put("message","输入的姓名有误");
                return result;
            }

            StudentTemp studentTemp = studentTempService.getByNoAndTenantIdAndName(no, tenantId, name);

            if(studentTemp == null){
                result.put("status","1");
                result.put("message","学校尚未导入您的学生信息");
                return result;
            }else if(studentTemp.getImportFlag()!=null&&studentTemp.getImportFlag()){
                result.put("status","1");
                result.put("message","该身份已被其他用户认证，如有疑问请联系客服。");
                return result;
            }

            Integer count = studentDao.countByUserIdAndTenantId(userId,tenantId);
            if(count > 0){
                result.put("status","1");
                result.put("message","您已认证别的学号，请勿重复认证。");
                return result;
            }

            User users = userDao.getById(userId);

            if(users == null){
                result.put("status","1");
                result.put("message","用户不存在");
                return result;
            }else{
                users.setName(name);
            }

            boolean flag = bindingStudent(users, studentTemp);
            if(flag){
                result.put("status","0");
                result.put("message","绑定成功");
                CombinedAccount combinedAccount = combinedAccountDao.getByUserId(userId);
                if(combinedAccount!=null && StringTools.isNotBlank(combinedAccount.getUnionId())){
                    userInfoService.updateUserLock(2,combinedAccount.getUnionId());
                }
            }else{
                result.put("status","1");
                result.put("message","绑定失败");
            }

        }catch(Exception e){
            throw new ServiceException("绑定失败");

        }finally {
            cacheService.remove(key);
        }
        return result;

    }

    private boolean bindingStudent(User users, StudentTemp studentTemp) {
        Date now = DateTools.getCurrentDateTime();
        Student students = new Student();

        BeanTools.copyNotNullProperties(students, studentTemp);

        Tenant tenant = tenantDao.getById(studentTemp.getTenantId());
        if (tenant != null) {
            students.setSchoolName(tenant.getName());
            studentTemp.setSchoolName(tenant.getName());
        }
        //更新学生
        students.setName(users.getName());
        students.setDeleteFlag(DomainConstants.DELETE_FLAG_AVAILABLE);
        students.setConfirmedAt(now);
        students.setRemark(DomainConstants.STUDENT_REMARK_IMPORT);
        students.setUserId(users.getUserId());
        students.setCreatedAt(now);
        students.setUpdatedAt(now);
        studentDao.save(students);

        //更新学生临时表
        studentTemp.setMobile(users.getMobile());
        studentTemp.setEmail(users.getEmail());
        studentTemp.setName(users.getName());
        studentTemp.setStudentId(students.getStudentId());
        studentTemp.setUserId(users.getUserId());
        studentTemp.setImportFlag(true);
        studentTemp.setConfirmedAt(now);
        studentTempDao.update(studentTemp);

        //更新用户
        users.setConfirmStatus(DomainConstants.USER_CONFIRM);
        users.setUpdatedAt(now);

        userDao.update(users);

        //更新班组表
        List<ClassGroupUser> cgus = classGroupUserDao.listByNoAndTenantId(students.getNo(), students.getTenantId());

        if(CollectionTools.isNotEmpty(cgus)){
            for(ClassGroupUser classGroupUser : cgus){
                classGroupUser.setBindingFlag(true);
                classGroupUser.setName(users.getName());
                classGroupUser.setStudentUserId(users.getUserId());
                classGroupUser.setUpdatedAt(now);
            }
            classGroupUserDao.batchUpdateList(cgus);
        }

        return true;
    }

    public boolean updateTenantIdRedis(String accessToken, String tenantId){
        String tokenKey = DomainConstants.GXB_WECHAT_TOKEN_FREFIX+accessToken;
        AuthToken authToken = (AuthToken) cacheService.getObject(tokenKey);
        if(authToken==null){
            return false;
        }
        authToken.setTenantId(StringTools.getLong(tenantId));
        cacheService.putObject(tokenKey,authToken,30*24*60*60l);
        return true;
    }

    public Map getUserInfo(long userId) {
        Map<String,Object> result = new HashMap<>();
        User user = userDao.getById(userId);
        assetService.setUserAvatar(user);
        Student student = studentDao.getBindingInfo(userId);
        if(student!=null){
            user.setSchoolName(student.getTenantName());
        }
        ElectiveUserInfo userinfo = userinfoDao.getUserInfoByUserId(StringTools.getInteger(StringTools.getString(userId)));
        if(userinfo!=null && 1==userinfo.getExchangecredits()){
            result.put("score","1");
        }else{
            result.put("score","0");
        }
        result.put("user",user);
        return result;
    }
}
