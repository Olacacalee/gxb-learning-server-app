package com.gxb.modules.domain.fixorder;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by xing on 2017/9/21.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FixOrder extends BasicDomain {

    private Long fixOrderId;
    private String carType;
    private String carNumber;
    private String driver;
    private String driverMobile;
    private Date sendTime;
    private String fixStatus;
    private Date finishTime;
    private Double price;
    private String fixer;

}
