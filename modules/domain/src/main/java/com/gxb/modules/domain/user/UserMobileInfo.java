package com.gxb.modules.domain.user;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2016/4/11.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserMobileInfo extends BasicDomain {
    private String userId;
    private String registrationId;
    private String imei;
}
