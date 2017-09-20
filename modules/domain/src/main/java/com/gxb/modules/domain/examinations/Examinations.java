package com.gxb.modules.domain.examinations;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by He on 2016/10/16.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Examinations extends BasicDomain {

    private int id;
    private int courceid;
    private String title;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String rightanswer;
    private Date creationtime;
    private int deleted;
    private int sort;
    private int type;
    private List<Options> optionsList;
    private int testtime;

}
