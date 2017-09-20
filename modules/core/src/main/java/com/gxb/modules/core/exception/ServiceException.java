package com.gxb.modules.core.exception;

import com.gxb.modules.core.web.vo.ErrorCode;

/**
 * Service 层公共异常，提示当前异常错误消息，及事务回滚
 * 
 * @author lh
 * @date 2015年7月1日
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -6336125614015879324L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(ErrorCode errorCode) {
		super(errorCode.message());
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
