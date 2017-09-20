package com.gxb.modules.domain.useranswer;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by He on 2016/10/20.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserAnswer extends BasicDomain {

    private Long userId;
    private long id;
    private long userid;
    private long examid;
    private String answer;
    private int result;
    private int score;
    private Date creationtime;
    private int deleted;

    private String useranswer;

}
