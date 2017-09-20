package com.gxb.modules.domain.electiveuesrinfo;

import com.gxb.modules.core.domain.BasicDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhaobin
 * @date 2015年12月22日
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ElectiveUserInfo extends BasicDomain {


    private Long user_id;//用户id
    private Long id;//自增id
    private Integer personalitytype;//人格类型
    private String selfconscious;//自我意识
    private String personalitycharacteristics;//人格特征
    private String careerplanning;//生涯规划
    private String Learningpsychology;//学习心理
    private String emotionalmanagement; //情绪管理

    private String interpersonalcommunication;//人际交往
    private String loveemotion;//恋爱情感
    private String unionid;
    private String pressuremanagement;//压力管理
    private Integer exchangecredits;//是否兑换学分
    private Date creationtime;
    private Integer deleted;
    private Integer credits;
    private Integer userlock;//该用户解锁的最大章节
    private Long userId;
    private Date reportTime;
}
