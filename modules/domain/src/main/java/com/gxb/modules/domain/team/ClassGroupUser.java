package com.gxb.modules.domain.team;

import com.gxb.modules.core.domain.BasicDomain;
import com.gxb.modules.domain.student.StudentTemp;
import com.gxb.modules.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassGroupUser extends BasicDomain {
    private static final long serialVersionUID = 5179279597707488424L;
    private Long groupUserId;
    private Long groupId;
    private String groupName;
    private Long classId;
    private Long userId;
    private Long editorId;
    private Long tenantId;
    private Long studentUserId;
    private String source;
    private String status;
    private String no;
    private boolean bindingFlag;
    private StudentTemp student;
    private User user;
    private Class clazz;
    private String name;
    private String avatar;
    private String mobile;
    private String email;
}