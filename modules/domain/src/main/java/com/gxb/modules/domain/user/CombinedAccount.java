package com.gxb.modules.domain.user;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CombinedAccount extends BasicDomain {

    private static final long serialVersionUID = -1418986572145914922L;
    private Long combinedAccountId;
    private Long userId;
    private String loginKey;
    private String keyType;
    private String nickName;
    private String unionId;
    private String openId;
    private Integer deleteFlag;

    private String mobile;

    //机构类型：高校 企业 营销租户 测试租户 自用租户 其他
    public enum LoginKeyType {
        WECHAT, QQ
    }

}