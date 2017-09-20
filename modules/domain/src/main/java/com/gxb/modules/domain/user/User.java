package com.gxb.modules.domain.user;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhaobin
 * @date 2015年12月22日
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BasicDomain {

    private static final long serialVersionUID = 4394331150956830612L;

    private Long userId;
    private String username;
    private String name;
    private String gender;
    private String mobile;
    private String email;
    private String encryptedPassword; //密码,
    private Integer confirmStatus; //认证状态 10未认证 20已认证
    private Integer status; //用户状态 10未激活 20正常 30禁用
    private String uuid;
    private String accessToken;
    private String openId;
    private String avatarUrl;
    private String unionId;
    private String validateCode;
    private String schoolName;

}
