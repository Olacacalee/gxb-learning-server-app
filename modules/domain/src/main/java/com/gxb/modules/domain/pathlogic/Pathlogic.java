package com.gxb.modules.domain.pathlogic;

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
public class Pathlogic extends BasicDomain {

    private int id;
    private int personalitytype;
    private int courceid;
    private int sort;
    private Date creationtime;
    private int deleted;
    private int cource;
    private String unionid;

}
