package com.gxb.modules.domain.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Administrator on 2016-10-20.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ElectiveFeedBack extends BasicDomain {
    private Long id;
    private String unionId;
    private String text;
    private Date createdAt;
}
