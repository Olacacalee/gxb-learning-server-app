package com.gxb.modules.domain.rule;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by He on 2016/10/19.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Rule extends BasicDomain {

    private long id;
    private long courceid;
    private int count;
    private int min_score;
    private int max_score;
    private String description;
    private String result;
    private String report;
    private Date created_at;
    private String loveEmotion;
    private String unionid;

}
