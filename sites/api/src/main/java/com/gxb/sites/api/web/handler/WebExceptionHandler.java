/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.gxb.sites.api.web.handler;

import com.gxb.modules.core.exception.ServiceException;
import com.gxb.modules.core.web.vo.ErrorVo;
import com.gxb.modules.utils.StringTools;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 异常处理器
 * 
 * @author lh
 * @date 2015年10月23日
 */
@ControllerAdvice
public class WebExceptionHandler {

	private Logger logger= LoggerFactory.getLogger(WebExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;


	
	/**
	 * http code 400
	 */
	@ExceptionHandler(value = { NullPointerException.class, NumberFormatException.class, TypeMismatchException.class, ServletException.class , HttpMessageConversionException.class })
	@ResponseBody
	public ResponseEntity<Object> handleBadReqeustException(Exception ex) {
		logger.debug("error",ex.getCause());

		ErrorVo errorVo = ErrorVo.newRootCauseErrorVo(ex, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(errorVo, HttpStatus.BAD_REQUEST);
	}

	/**
	 * http code 422 处理JSR311 Validation异常.
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseBody
	public ResponseEntity<Object> handleUnprocessableEntityException(Exception ex) {
		logger.debug("error",ex.getCause());
		
		ErrorVo errorVo = ErrorVo.newRootCauseErrorVo(ex, HttpStatus.UNPROCESSABLE_ENTITY.value());
		return new ResponseEntity<Object>(errorVo, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * http code 500
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ResponseEntity<Object> handleException(HttpServletRequest request,Exception ex,Object handler) {
		if(ex instanceof MyBatisSystemException){
			return handleBadReqeustException(ex);
		}
		ex.printStackTrace();
		logger.error("error",ex.getCause());
		//暂时没有用到mq，将下面放入到mq队列的调用注释掉
//		logMessageProducer.send(getExceptionLog(request,ex));
		
		ErrorVo errorVo = ErrorVo.newRootCauseErrorVo(ex, HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Object>(errorVo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * http code 1000 处理ServiceException.
	 */
	@ExceptionHandler(value = { ServiceException.class })
    @ResponseBody
    public HttpEntity<Object> handleServiceException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        // refactor static variable
        logger.debug("error",ex.getCause());
		//暂时没有用到mq，将下面放入到mq队列的调用注释掉
//        logMessageProducer.send(getExceptionLog(request,ex));

        response.setStatus(1000);
        ErrorVo errorVo = ErrorVo.newRootCauseErrorVo(ex, 1000);
        return new HttpEntity<Object>(errorVo);
    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<Object> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		ErrorVo errorVo = new ErrorVo(processFieldErrors(fieldErrors),MethodArgumentNotValidException.class,HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(errorVo, HttpStatus.BAD_REQUEST);
	}

	private String processFieldErrors(List<FieldError> fieldErrors) {
		StringBuffer buffer = new StringBuffer();
		for (FieldError fieldError: fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			buffer.append(fieldError.getField()).append(localizedErrorMessage).append(",");
		}

		return buffer.toString();
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale =  LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
		//If the message was not found, return the most accurate field error code instead.
		//You can remove this check if you prefer to get the default error message.
		/*if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}*/

		return localizedErrorMessage;
	}

}
