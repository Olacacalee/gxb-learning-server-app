package com.gxb.modules.domain.tenant;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by xing on 2017/9/30.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tenant extends BasicDomain {

    private Long tenantId;
    private String tenantName;
    private String shortname;

}
