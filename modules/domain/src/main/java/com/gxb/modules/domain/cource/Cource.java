package com.gxb.modules.domain.cource;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhaobin
 * @date 2015年12月22日
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cource extends BasicDomain {



    private Integer id;
    private String cource;
    private String courcedescribe;
    private Integer type;
    private String source;
    private Integer duration;
    private Date creationtime;
    private Integer deleted;
    private Integer sort;
    private Integer parentid;
    private Integer schedule;
    private Integer usid;//用户进度表自增id
    private Integer userid;//用户id
    private String unionid;

}
