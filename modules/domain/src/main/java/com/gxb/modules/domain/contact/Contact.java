package com.gxb.modules.domain.contact;

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
public class Contact extends BasicDomain{

    private Long contactId;
    private String contactName;
    private String contactMobile;
    private String contactCarType;
    private String contactCarNumber;

}
