package com.gxb.modules.utils;

/**
 * Created by lou on 16/3/22.
 */
public class MessagePushTools {

    public static String getPushAnnouncementText(String className) {
        return "《" + className + "》有新的课程公告，点击查看。";
    }

    public static String getPublishUnitText(String className, String UnitTitle) {
        return "《" + className + "》章节:" + UnitTitle + ",已发布，点击进入学习。";
    }

    public static String getUpdateUnitText(String className, String UnitTitle) {
        return "《" + className + "》章节:" + UnitTitle + ",有新的更新，点击进入学习。";
    }

    public static String getEndQuizText(String className, String quizTitle, String end_time) {
        return "《" + className + "》测验:" + quizTitle + ",将在" + end_time + "截止,过期将无法获得成绩,请及时去网页端完成。";
    }

    public static String getEndAssignmentText(String className, String assignmentTitle, String end_time) {
        return "《" + className + "》作业:" + assignmentTitle + ",将在" + end_time + "截止,过期将无法获得成绩,请及时去网页端完成。";
    }

    public static String getPublishPeerReviewText(String className, String assignmentTitle) {
        return "《" + className + "》作业:" + assignmentTitle + ",互评已开始,请及时去网页端完成互评及自评。";
    }

    public static String getEndPeerReviewText(String className, String assignmentTitle, String end_time) {
        return "《" + className + "》作业:" + assignmentTitle + ",互评将在" + end_time + "截止,不参与互评会影响您的成绩,请及时去网页端完成互评及自评。";
    }

    public static String getPublishScoreAssignmentText(String className, String assignmentTitle) {
        return "《" + className + "》作业:" + assignmentTitle + ",成绩已经公布,请及时去网页端查看。";
    }

    public static String getNewPostText(String className, String TopicTitle) {
        return "《" + className + "》讨论:" + TopicTitle + ",有新的回复,请及时去网页端查看。";
    }

    public static String getUpdateAssessmentText(String className) {
        return "《" + className + "》评分标准有了最新的更新,请及时去网页端查看。";
    }

    public static String getPublishScoreClassText(String className) {
        return "《" + className + "》课程成绩已经公布,请及时去网页端查看。";
    }

    public static String getPublishClassText(String className) {
        return "《" + className + "》课程已经开课,点击进入学习。";
    }

    public static String getEndClassText(String className, String end_time) {
        return "《" + className + "》课程将在" + end_time + "结课,结课之后所有学习将不再计入成绩,点击进入学习。";
    }

    public static String getLowClassText(String className) {
        return "《" + className + "》您的学习进度仅仅击败了20%的同学,抓紧学习去干超把。";
    }

    public static String getDelayAssignmentText(String className, String assignmentTitle, String end_time) {
        return "《" + className + "》作业:" + assignmentTitle + "已延长至" + end_time + ",请及时去网页端完成。";
    }

    public static String getAgainAssignmentText(String className, String assignmentTitle) {
        return "《" + className + "》作业:" + assignmentTitle + ",老师要求需要重做,请及时去网页端完成。";
    }
}
