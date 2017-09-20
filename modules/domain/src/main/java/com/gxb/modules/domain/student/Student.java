package com.gxb.modules.domain.student;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhaobin
 * @date 2016年01月07日
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends BasicDomain {

    private static final long serialVersionUID = -1418986572145914922L;
    private Long studentId;
    private Long userId;
    private String no;
    private String name;
    private String gender;
    private String mobile;
    private String email;
    private String major; //'专业',
    private String schoolName; //'学校名称',
    private String schoolDepartment; //'院系',
    private String schoolGrade; //'年级',
    private String schoolClass; //'班级',
    private Date enrollmentAt; //'入学年份',
    private Integer status;  //'学生状态 10在校 20校外实习 30毕业 40社会用户',
    private Integer remark;  //'学生来源 10学校导入 20自由注册'
    private Date confirmedAt; //'认证时间'
    private Long tenantId;

    private String groupName;
    private BigDecimal score;
    private Long tempId;
    private String tenantName;
}
