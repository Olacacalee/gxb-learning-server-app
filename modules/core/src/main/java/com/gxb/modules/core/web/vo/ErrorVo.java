package com.gxb.modules.core.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.Throwables;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * 
 * @author lh
 * @date 2015年10月26日
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ErrorVo extends ResponseViewVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String message;
	private String type;
	private Integer code;
	
	public static ErrorVo newErrorVo(Throwable throwable, Integer code){
		return new ErrorVo(throwable, code);
	}
	
	public static ErrorVo newRootCauseErrorVo(Throwable throwable, Integer code){
		return new ErrorVo(Throwables.getRootCause(throwable), code);
	}
	
	public ErrorVo(Throwable throwable, Integer code) {
		this(throwable.getMessage(), throwable.getClass(), code);
	}

	public ErrorVo(String message, Class<?> clazz, Integer code) {
		this.message = message;
		this.type = clazz.getSimpleName();
		this.code = code;
	}

	public Integer getCode(){
		return code;
	}
	
}
