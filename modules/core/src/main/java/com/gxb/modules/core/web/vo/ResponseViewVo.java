package com.gxb.modules.core.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by zhangjinxia on 15/12/18.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseViewVo<T> {

    protected String message;
    protected Integer code;
    protected T data;

    public static ResponseViewVo success(){
        return SuccessVo.success();
    }

    public static ResponseViewVo error(Throwable ex, Integer code){
        return new ErrorVo(ex,code);
    }

}
