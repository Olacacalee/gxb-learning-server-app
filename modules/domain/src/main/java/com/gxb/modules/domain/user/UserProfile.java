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
public class UserProfile extends BasicDomain {

    private static final long serialVersionUID = 4394331150956830612L;
    private Long userId;
    private String registerFrom;//注册来源
    private String platform;//注册客服端 ios android chrome ie
    private Integer failAttempts;

    public enum registerFrom {
        qq, sina, weixin, baidu ,wangyi
    }
}
