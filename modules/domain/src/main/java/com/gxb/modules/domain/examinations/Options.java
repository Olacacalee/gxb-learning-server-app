package com.gxb.modules.domain.examinations;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2016-10-17.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Options extends BasicDomain {

    private String type;
    private String content;
}
