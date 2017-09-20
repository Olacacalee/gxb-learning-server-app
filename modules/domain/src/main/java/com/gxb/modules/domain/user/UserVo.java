package com.gxb.modules.domain.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaobin
 * @date 2015年12月26日
 */

@Data
public class UserVo implements Serializable {


    private static final long serialVersionUID = 486789303244814690L;
    private Long userId;
    private String username;
    private String name;
    private String gender;
    private String mobile;
    private String email;
    private String encrypted_password;
    private Integer status;
    private String registerFrom;
    private String platform;
    private String validateCode;
    private String tenantId;
    private List<String> roles;


}
