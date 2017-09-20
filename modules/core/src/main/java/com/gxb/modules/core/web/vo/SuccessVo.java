package com.gxb.modules.core.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Created by zhangjinxia on 15/12/18.
 */
@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SuccessVo<T> extends ResponseViewVo {
    private static final long serialVersionUID = 1L;

    public SuccessVo(T data){
        this.message=HttpStatus.OK.getReasonPhrase();
        this.code=HttpStatus.OK.value();
        this.data=data;
    }

    public SuccessVo(String message,Integer code){
        this.message=message;
        this.code=code;
    }

    public SuccessVo(String message,Integer code,T data){
        this.message=message;
        this.code=code;
        this.data=data;
    }

    public static SuccessVo success(){
        return new SuccessVo(HttpStatus.OK.getReasonPhrase(),HttpStatus.OK.value());
    }

}
