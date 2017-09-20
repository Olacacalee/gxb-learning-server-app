package com.gxb.modules.core.exception;

/**
 * MQ异常，提示当前异常错误消息
 * 
 * @author lh
 * @date 2015年11月20日
 */
public class MqException extends RuntimeException {
	private static final long serialVersionUID = -6336125614015879324L;
	
	public MqException() {
		super();
	}

	public MqException(String message) {
		super(message);
	}

	public MqException(Throwable cause) {
		super(cause);
	}

	public MqException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
