package com.gxb.modules.domain.userschedule;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by He on 2016/10/16.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserSchedule extends BasicDomain {

    private Long userId;
    private int id;
    private long courceid;
//    private long userid;
    private int schedule;
    private Date creationtime;
    private String unionId;


}
