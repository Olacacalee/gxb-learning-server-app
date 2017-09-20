package com.gxb.modules.constants;

import java.math.BigDecimal;

/**
 * @author gtli
 * @date 15/11/11
 */
public interface DomainConstants {
    String GXB_SSO_LOGIN_MSG = "<input type=\"hidden\" name=\"hiddenView\" value=\"welcome gaoxiaobang\"/>";

    String VISIT_TENANT_ID = "visitTenantId";

    String VISIT_URL = "visitUrl";
    //可用状态，未删除
    Integer DELETE_FLAG_AVAILABLE = 1;
    //不可用，删除
    Integer DELETE_FLAG_DISABLE = 0;
    //从回收站删除
    Integer DELETE_FLAG_RECYCLE_DISABLE = -1;
    //私有
    Integer IS_PRIVATE = 1;
    //公有
    Integer IS_PUBLIC = 0;
    //根节点的parent_id
    Long ROOT_PARENT_ID = -1L;
    //文件夹资源的context_id
    Long RESOURCE_FOLDER_CONTENT_ID = -1L;
    //试题文件夹的question_id
    Long QUESTION_FOLDER_QUESTION_ID = -1L;
    //视频类型
    String CONTENT_TYPE_VIDEO = "video";
    //所有类型
    String CONTENT_TYPE_All = "all";
    //文档类型
    String CONTENT_TYPE_DOC = "document";
    //试题类型
    String CONTENT_TYPE_QUESTION = "question";
    //文件夹类型
    String FILE_TYPE_FOLDER = "folder";
    //文件类型
    String FILE_TYPE_FILE = "file";

    Integer QUIZ_ALLOWED_ATTEMPTS = 3;

    //正确
    Integer QUESTION_CORRECT = 1;
    //错误
    Integer QUESTION_ERROR = 0;
    //客户端
    String CLIENT = "client";

    String App = "app";
    //网页端
    String WEB = "web";

    String WECHAT = "wechat";

    String CONTEXT_TYPE_CLASS = "class";

    String CONTEXT_TYPE_COURSE = "course";

    //班次类型
    //辅修
    String CLASS_TYPE_MINOR = "10";
    //学分
    String CLASS_TYPE_CREDIT = "20";

    //班次使用性质
    //自用
    String CLASS_USE_TYPE_SELF = "10";
    //售卖
    String CLASS_USE_TYPE_SELL = "20";

    String RESOURCE_NODE_DEFAULT_NAME = "默认目录";

    //班次教学模式
    //开放学习模式
    String CLASS_TEACH_MODE_OPEN = "10";
    //传统学习模式
    String CLASS_TEACH_MODE_TRADITION = "20";

    Integer DEFAULT_PAGE_SIZE = 20;

    //题目选项是正确的
    Integer OPTION_CORRECT_TRUE = 1;
    //题目选项是错误的
    Integer OPTION_CORRECT_FALSE = 0;

    String GXB_WECHAT_TOKEN_FREFIX = "gxb:wechat:user:token:";

    //用户被激活
    Integer USER_ACTIVATED = 20;
    //用户未被激活
    Integer USER_NOT_ACTIVATED = 10;

    //未发送验证码
    Integer RESET_PWD_TOKEN_UNSENDED = 0;
    //发送验证码未验证
    Integer RESET_PWD_TOKEN_SENDED = 10;
    //验证码已被验证
    Integer RESET_PWD_TOKEN_VALIDATED = 20;

    //用户未被认证
    Integer USER_NOT_CONFIRM = 10;
    //用户已被认证
    Integer USER_CONFIRM = 20;

    //用户来源
    //导入
    Integer STUDENT_REMARK_IMPORT = 10;
    //注册
    Integer STUDENT_REMARK_REGISTER = 20;
    //oauth第三方注册
    Integer STUDENT_REMARK_OAUTH = 30;

    String COURSE_PROGRESS = "progress";

    String COURSE_UNSTART = "unstart";

    String COURSE_CONCLUDE = "conclude";

    //班次开课时间距离当天天数
    Integer CLASS_START_AT_DAY = 1;
    //班次结课时间距离开课时间月数
    Integer CLASS_CONCLUDE_AT_MON = 4;

    String DEFAULT_CLASS_GROUP = "默认分组";

    Integer ROUND_MODE = BigDecimal.ROUND_HALF_UP;

    Integer BIGDECIMAL_SCALE=2;

    String MULTIPLE_CHOICE="multiple_choice";

    String MULTIPLE_ANSWER="multiple_answers";

    String FILL_IN_BLANK = "fill_in_blank";


    Integer SUBMISSION_DISTRIBUTION =1;//提交的作业已经被分配

    Integer SUBMISSION_NOT_DISTRIBUTION =0;//未被分配

    Integer SUBMISSION_CALCULATOR =1;//提交的作业已经被计算

    Integer SUBMISSION_NOT_CALCULATOR =0;//未被计算

    Integer PERSONALITY_TYPE_A = 1;
     Integer PERSONALITY_TYPE_B = 2;
     Integer PERSONALITY_TYPE_C = 3;
     Integer PERSONALITY_TYPE_D = 4;
    Integer PERSONALITY_TYPE_E = 5;
    Integer PERSONALITY_TYPE_F = 6;
    Integer PERSONALITY_TYPE_G = 7;
    Integer PERSONALITY_TYPE_H = 8;
    Integer PERSONALITY_TYPE_I = 9;
}
