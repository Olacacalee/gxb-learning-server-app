package com.gxb.modules.domain.student;

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
public class StudentTemp extends BasicDomain {

    private static final long serialVersionUID = 4394331150956830612L;
    private Long id;
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
    private Date confirmedAt; //'认证时间'
    private Long tenantId;
    private Boolean importFlag;

}
