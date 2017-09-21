package com.gxb.modules.domain.result;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by xing on 2017/9/21.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResultObject extends BasicDomain{

    private String status;
    private String message;
    private Object data;

    public ResultObject(String status,String message,Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
