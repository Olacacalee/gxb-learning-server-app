package com.gxb.modules.core.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaobin
 * @date 2016年01月14日
 */

@Data
public class ResultObj {

    private boolean status;

    private Object data;

    private Map<String,Object> errorInfo;

    public ResultObj(){
        this.status = true;
        this.errorInfo = new HashMap<>();
    }

    public void putErrorMsg(String key,String info){

        if(errorInfo.get(key) != null){
            return;
        }
        errorInfo.put(key,info);
        status = false;

    }

}
