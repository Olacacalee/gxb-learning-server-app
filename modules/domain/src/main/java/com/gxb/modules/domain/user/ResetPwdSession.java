package com.gxb.modules.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhaobin
 * @date 2016年01月06日
 */

@Data
public class ResetPwdSession implements Serializable {

    private static final long serialVersionUID = -6499704075930596012L;

    private String mobile;
    private String email;
    @JsonIgnore
    private Integer status;//0:token未发送 10:token已发送 20:token 已被验证
    @JsonIgnore
    private String mobileCode;
    @JsonIgnore
    private String emailCode;
    @JsonIgnore
    private Date mobileCodeSendAt;
    @JsonIgnore
    private Date emailCodeSendAt;
    @JsonIgnore
    private String imageCode;

    public enum KEYTYPE {
        email,mobile
    }
}
