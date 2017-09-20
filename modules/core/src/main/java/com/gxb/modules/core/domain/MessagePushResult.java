package com.gxb.modules.core.domain;

import lombok.Data;

/**
 * Created by Administrator on 2016/3/30.
 */
@Data
public class MessagePushResult {

    private int status;
    private String result;
    private boolean isSuccess = true;
    private Object data;
    private String errMessage;

    public void setErrMessage(String errMessage){
        this.isSuccess = false;
        this.errMessage = errMessage;
    }
}
