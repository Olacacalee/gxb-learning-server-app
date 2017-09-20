package com.gxb.sites.api.resource.examinations.service;

import com.gxb.modules.constants.DomainConstants;
import com.gxb.modules.domain.cource.Cource;
import com.gxb.modules.domain.electiveuesrinfo.ElectiveUserInfo;
import com.gxb.modules.domain.examinations.Examinations;
import com.gxb.modules.domain.examinations.Options;
import com.gxb.modules.domain.rule.Rule;
import com.gxb.modules.domain.useranswer.UserAnswer;
import com.gxb.modules.utils.PersonalityTools;
import com.gxb.sites.api.config.constant.TransactionConstant;
import com.gxb.sites.api.resource.cource.dao.CourceDao;
import com.gxb.sites.api.resource.examinations.dao.ExaminationsDao;
import com.gxb.sites.api.resource.pathlogic.dao.PathlogicDao;
import com.gxb.sites.api.resource.rule.dao.RuleDao;
import com.gxb.sites.api.resource.useranswer.dao.UserAnswerDao;
import com.gxb.sites.api.resource.userinfo.dao.UserInfoDao;
import com.gxb.sites.api.resource.userschedule.dao.UserScheduleDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by He on 2016/10/16.
 */
@Service
public class ExaminationsService {

    @Autowired
    private ExaminationsDao examinationsDao;
    @Autowired
    private PathlogicDao pathlogicDao;
    @Autowired
    private CourceDao courceDao;
    @Autowired
    private RuleDao ruleDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserScheduleDao userScheduleDao;
    @Autowired
    private UserAnswerDao userAnswerDao;

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;

    public Map<String, Object> findALLExaminations(int courceid, String unionid) {
//        int courceid = pathlogicDao.findALLExaminations(cource, personalitytype);
        Map<String, Object> map = new HashMap<>();

        List<Examinations> list = examinationsDao.findALLExaminations(courceid);
        for (Examinations exam : list) {
            List<Options> optionsList = new ArrayList<>();
            Options a = new Options();
            a.setType("A");
            a.setContent(exam.getA());
            optionsList.add(a);

            Options b = new Options();
            b.setType("B");
            b.setContent(exam.getB());
            optionsList.add(b);

            if (exam.getC() != null) {
                Options c = new Options();
                c.setType("C");
                c.setContent(exam.getC());
                optionsList.add(c);
            }

            if (exam.getD() != null) {
                Options d = new Options();
                d.setType("D");
                d.setContent(exam.getD());
                optionsList.add(d);
            }
            if (exam.getE() != null) {
                Options e = new Options();
                e.setType("E");
                e.setContent(exam.getE());
                optionsList.add(e);
            }

            exam.setOptionsList(optionsList);

            map.put("status", 0);
            map.put("tests", list);
        }
        ElectiveUserInfo getuserInfo = userInfoDao.getUserInfoByUserUnionID(unionid);
        if (getuserInfo.getPersonalitytype() != 0 && courceid == 2) {
            Rule rule = ruleDao.getUserTestResult(getuserInfo.getPersonalitytype(), courceid);
            map.put("escription", rule.getDescription());
            map.put("status", 1);
            map.put("Personalitytype", getuserInfo.getPersonalitytype());
            map.put("Personalitycharacteristics", getuserInfo.getPersonalitycharacteristics());
        }
        Cource testinfo = courceDao.getTitleByCourceid(courceid);
        String title = testinfo.getCource();
        map.put("testtitle", title);
        return map;
    }

    @Transactional(TransactionConstant.LCMS)
    public Map AnalysisTheAnswer(int courceid, String unionid, List userAnswer) {
        Map<String, Object> resultmap = new HashMap();
        Cource testinfo = courceDao.getTitleByCourceid(courceid);
        String testtitle = testinfo.getCource();
        int type = testinfo.getType();
        List<Examinations> answerlist = examinationsDao.findALLRightAnwsers(courceid);
        long userid = userInfoDao.getUserIDInfoByUserUnionID(unionid);
        int right = 0;
        int wrong = 0;
        int score = 0;
        String desc = null;
        String result = null;
        String report = null;
        ElectiveUserInfo userInfo = new ElectiveUserInfo();
        List<Integer> idlist = new ArrayList();
        if (type == 0) {//type = 0 psychtest
            if (courceid == 2) { //九型人格
                Map<String, Integer> map = PersonalityAnalysis(answerlist, userAnswer);
                int personalitytype = map.get("personalitytype");
                int personality = userInfoDao.findPersonalityByUnionId(unionid);
                if (personality != 0) {
                    userInfoDao.updateUserLock(2, userid);
                    userScheduleDao.updateUserScheduleToDeleted(unionid);
                }
                score = map.get("score");
                Rule rule = ruleDao.getUserTestResult(personalitytype, courceid);
                desc = rule.getDescription();
                result = rule.getResult();
                report = rule.getReport();
                String loveemotion = rule.getLoveEmotion();
                resultmap.put("score", score);
                resultmap.put("desc", desc);
                resultmap.put("result", result);
                userInfo.setPersonalitytype(personalitytype);//人格
                userInfo.setPersonalitycharacteristics(report);//人格报告
                userInfo.setLoveemotion(loveemotion);//恋爱与情感
            }
            if (courceid == 5) {//情绪情绪管理能力自我测试
                score = EmotionalManagementAnalysis(answerlist, userAnswer);
                if (score >= 130) {
                    int schedule = 0;
                    userScheduleDao.saveUserSchedule(userid, courceid + 1, schedule, unionid);//Skip the next chapter
                    List<Cource> courcelist = courceDao.getCourceByParentId(courceid + 1);
                    for (int j = 0; j < courcelist.size(); j++) {
                        userScheduleDao.saveUserSchedule(userid, courcelist.get(j).getId(), schedule, unionid);
                    }
                }
                Rule rule = ruleDao.getUserTestResult(score, courceid);
                desc = rule.getDescription();
                result = rule.getResult();
                report = rule.getReport();
                userInfo.setEmotionalmanagement(report);//情绪自我管理
            }
            if (courceid == 8) {//自我意识量表
                Map<String, Integer> map = SelfConsciousAnalysis(answerlist,userAnswer,courceid);
                score = map.get("score");
                if (score >= 0) {
                    desc = "公众自我的人";
                    Rule rule = ruleDao.getUserSelfConsciousness(courceid, desc);
                    result = rule.getResult();
                    report = rule.getReport();
                } else {
                    desc = "内在自我的人";
                    Rule rule = ruleDao.getUserSelfConsciousness(courceid, desc);
                    result = rule.getResult();
                    report = rule.getReport();
                }
                score = Math.abs(score);
                resultmap.put("desc", desc);
                resultmap.put("result", result);
                userInfo.setSelfconscious(report);//自我意识
            }
            if (courceid == 15) {//PSTR心理压力测试
                for (int i = 0; i < answerlist.size(); i++) {
                    int id = answerlist.get(i).getId();
                    Map<Integer, String> anwsermap = (Map<Integer, String>) userAnswer.get(i);
                    String useranswer = anwsermap.get(id);
                    if ("A".equals(useranswer)) {
                        idlist.add(id);
                        score = score + FOUR;
                    }
                    if ("B".equals(useranswer)) {
                        idlist.add(id);
                        score = score + THREE;
                    }
                    if ("C".equals(useranswer)) {
                        idlist.add(id);
                        score = score + TWO;
                    }
                    if ("D".equals(useranswer)) {
                        idlist.add(id);
                        score = score + ONE;
                    }
                }
                Rule rule = ruleDao.getUserTestResult(score, courceid);
                desc = rule.getDescription();
                result = rule.getResult();
                report = rule.getReport();
                userInfo.setInterpersonalcommunication(report);
            }
            if (courceid == 17) {//人际关系综合诊断
                for (int i = 0; i < answerlist.size(); i++) {
                    int id = answerlist.get(i).getId();
                    Map<Integer, String> anwsermap = (Map<Integer, String>) userAnswer.get(i);
                    String useranswer = anwsermap.get(id);
                    if ("A".equals(useranswer)) {
                        idlist.add(id);
                        score = score + ONE;
                    }
                }
                Rule rule = ruleDao.getUserTestResult(score, courceid);
                desc = rule.getDescription();
                result = rule.getResult();
                report = rule.getReport();
                userInfo.setInterpersonalcommunication(report);
            }
//            userInfo.setUser_id(userid);
            userInfo.setUnionid(unionid);
            userInfoDao.update(userInfo);//update user psychtest
            resultmap.put("score", score);
            resultmap.put("desc", desc);
            resultmap.put("result", result);
        }

        if (type == 2) { //单元测验
            UserAnswer answerDomain = new UserAnswer();
            answerDomain.setUserId(userid);
            for (int i = 0; i < answerlist.size(); i++) {
                int id = answerlist.get(i).getId();
                String answer = answerlist.get(i).getRightanswer();

                Map<Integer, String> anwsermap = (Map<Integer, String>) userAnswer.get(i);
                String useranswer = anwsermap.get(id);

                answerDomain.setExamid(id);
                answerDomain.setAnswer(useranswer);
                int check = 0;
                if (useranswer.equals(answer)) {
                    right = right + ONE;
                } else {
                    wrong = wrong + ONE;
                    check = 1;
                }
                answerDomain.setAnswer(useranswer);
                answerDomain.setResult(check);
                userAnswerDao.save(answerDomain);//save the user anwser
            }
            resultmap.put("right", right);
            resultmap.put("wrong", wrong);
        }
        userScheduleDao.saveUserSchedule(userid, courceid, answerlist.size(), unionid);
        resultmap.put("type", type);
        resultmap.put("testtitle", testtitle);
        return resultmap;
    }

    //九型人格test
    public Map<String, Integer> PersonalityAnalysis(List<Examinations> answerlist, List userAnswer) {
        int score = 0;
        Map<String, Integer> map = new HashedMap();
        List<Integer> idlist = new ArrayList();
        for (int i = 0; i < answerlist.size(); i++) {
            for (int j = 0; j < userAnswer.size(); j++) {
                Map<Integer, String> userMap = (Map<Integer, String>) userAnswer.get(j);
                if ("A".equals(userMap.get(answerlist.get(i).getId()))) {
                    idlist.add(answerlist.get(i).getId());
                    score = score + ONE;
                }
            }
        }
        map.put("score", score);
        if (idlist.size() > 0) {
            Map<String, Integer> personality = examinationsDao.findThePersonality(idlist);
            Map<Integer, Integer> personalitymap = new HashMap<>();
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_A, Integer.valueOf(((Object) personality.get("a")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_B, Integer.valueOf(((Object) personality.get("b")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_C, Integer.valueOf(((Object) personality.get("c")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_D, Integer.valueOf(((Object) personality.get("d")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_E, Integer.valueOf(((Object) personality.get("e")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_F, Integer.valueOf(((Object) personality.get("f")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_G, Integer.valueOf(((Object) personality.get("g")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_H, Integer.valueOf(((Object) personality.get("h")).toString()));
            personalitymap.put(DomainConstants.PERSONALITY_TYPE_I, Integer.valueOf(((Object) personality.get("i")).toString()));
            int personalitytype = PersonalityTools.getPersonality(personalitymap);
            map.put("personalitytype", personalitytype);
            return map;
        } else {
            map.put("personalitytype", 1);
            return map;
        }
    }

    //情绪管理
    public int EmotionalManagementAnalysis(List<Examinations> answerlist, List userAnswer){
        int score = 0;
        for (int i = 0; i < answerlist.size(); i++) {
            int id = answerlist.get(i).getId();
            Map<Integer, String> anwsermap = (Map<Integer, String>) userAnswer.get(i);
            String useranswer = anwsermap.get(id);
            int sort = examinationsDao.getSortByid(id);
            if (sort >= 1 & sort <= 9) {
                if (useranswer.equals("A")) {
                    score = score + SIX;
                }
                if (useranswer.equals("B")) {
                    score = score + THREE;
                }
            }
            if (sort >= 10 & sort <= 25) {
                if (useranswer.equals("A")) {
                    score = score + FIVE;
                }
                if (useranswer.equals("B")) {
                    score = score + TWO;
                }
            }
            if (sort >= 26 & sort <= 29) {
                if (useranswer.equals("B")) {
                    score = score + FIVE;
                }
            }
            if (sort >= 30 & sort <= 33) {
                if (useranswer.equals("A")) {
                    score = score + ONE;
                }
                if (useranswer.equals("B")) {
                    score = score + TWO;
                }
                if (useranswer.equals("C")) {
                    score = score + THREE;
                }
                if (useranswer.equals("D")) {
                    score = score + FOUR;
                }
                if (useranswer.equals("E")) {
                    score = score + FIVE;
                }
            }
        }
        return score;
    }

    //自我意识量表
    public Map<String, Integer> SelfConsciousAnalysis(List<Examinations> answerlist, List userAnswer, int courceid) {
        int score = 0;
        Map<String, Integer> map = new HashedMap();
        for(int i = 0; i < answerlist.size(); i++) {
            int id = answerlist.get(i).getId();
            Map<Integer, String> anwsermap = (Map<Integer, String>) userAnswer.get(i);
            String useranswer = anwsermap.get(id);
//            List<Examinations> outtypelist = examinationsDao.findAllIdAndType(courceid, 0);
//            for (int j = 0; j < outtypelist.size(); j++) {
//                int outid = outtypelist.get(j).getId();
//                if (id == outid) {
//                    if ("B".equals(useranswer)) {
//                        score = score + ONE;
//                    }
//                    if ("C".equals(useranswer)) {
//                        score = score + TWO;
//                    }
//                    if ("D".equals(useranswer)) {
//                        score = score + THREE;
//                    }
//                    if ("E".equals(useranswer)) {
//                        score = score + FOUR;
//                    }
//                }
//            }
//            List<Examinations> intypelist = examinationsDao.findAllIdAndType(courceid, 1);
//            for (int j = 0; j < intypelist.size(); j++) {
//                int inid = intypelist.get(j).getId();
//                if (id == inid) {
//                    if ("B".equals(useranswer)) {
//                        score = score - ONE;
//                    }
//                    if ("C".equals(useranswer)) {
//                        score = score - TWO;
//                    }
//                    if ("D".equals(useranswer)) {
//                        score = score - THREE;
//                    }
//                    if ("E".equals(useranswer)) {
//                        score = score - FOUR;
//                    }
//                }
//            }
//            List<Examinations> disintypelist = examinationsDao.findAllIdAndType(courceid, -1);
//            for (int j = 0; j < disintypelist.size(); j++) {
//                int disinid = disintypelist.get(j).getId();
//                if (id == disinid) {
//                    if ("D".equals(useranswer)) {
//                        score = score - ONE;
//                    }
//                    if ("C".equals(useranswer)) {
//                        score = score - TWO;
//                    }
//                    if ("B".equals(useranswer)) {
//                        score = score - THREE;
//                    }
//                    if ("A".equals(useranswer)) {
//                        score = score - FOUR;
//                    }
//                }
//            }

            if (id == 89 || id == 92 || id == 95 || id == 97 || id == 99 || id == 101 || id == 103) {
                if ("B".equals(useranswer)) {
                    score = score + ONE;
                }
                if ("C".equals(useranswer)) {
                    score = score + TWO;
                }
                if ("D".equals(useranswer)) {
                    score = score + THREE;
                }
                if ("E".equals(useranswer)) {
                    score = score + FOUR;
                }
            }
            if (id == 88 || id == 91 || id == 93 || id == 96 || id == 98 || id == 100 || id == 102 || id == 104) {
                if ("B".equals(useranswer)) {
                    score = score - ONE;
                }
                if ("C".equals(useranswer)) {
                    score = score - TWO;
                }
                if ("D".equals(useranswer)) {
                    score = score - THREE;
                }
                if ("E".equals(useranswer)) {
                    score = score - FOUR;
                }
            }
            if (id == 90 || id == 94) {
                if ("D".equals(useranswer)) {
                    score = score - ONE;
                }
                if ("C".equals(useranswer)) {
                    score = score - TWO;
                }
                if ("B".equals(useranswer)) {
                    score = score - THREE;
                }
                if ("A".equals(useranswer)) {
                    score = score - FOUR;
                }
            }
        }
        map.put("score", score);
        return map;
    }


}
