/**
 *  Copyright (c)  2014-2020 Kaikeba, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Kaikeba,
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Kaikeba.
 */
package com.gxb.sites.api.web.filter;

import com.gxb.modules.core.web.filter.PerformanceMonitorInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * @author sunninghai
 * @date 2015年11月10日
 */
@Component
public class VisitLogFilter implements Filter{

	private static Logger logger = LoggerFactory.getLogger(PerformanceMonitorInterceptor.class);


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		BodyReaderHttpServletRequestWrapper wrapper = getVisitLog((HttpServletRequest)request);
		if(wrapper !=null)
			chain.doFilter(wrapper, response);
		else
			chain.doFilter(request, response);
	}





	private BodyReaderHttpServletRequestWrapper getVisitLog(HttpServletRequest request) {
		BodyReaderHttpServletRequestWrapper wrapper = null;
//		VisitLog visitLog = new VisitLog();
//		Date date = new Date();
//		visitLog.setDateTime(date);
//		visitLog.setDateTimestamp(date.getTime());
//		visitLog.setVisitLogId(IdentitieTools.uuid2());
//		visitLog.setUserId(StringTools.getLong(request.getParameter("userId")) );
//		visitLog.setToken(request.getParameter("token"));
//		visitLog.setRequestUri(request.getRequestURI());
//		visitLog.setAction(request.getMethod());
//		visitLog.setVisitTenantId(StringTools.getLong(request.getParameter(DomainConstants.VISIT_TENANT_ID)) );
//		visitLog.setVisitUrl(StringTools.getString(request.getParameter(DomainConstants.VISIT_URL)));
//		visitLog.setUserAgentInfo(new UserAgentInfo(request));
//		if (!StringTools.equalsString(request.getMethod(), RequestMethod.GET.toString())
//				&& !StringTools.equalsString(MediaType.MULTIPART_FORM_DATA_VALUE, request.getContentType())) {
//			try {
//				wrapper = new BodyReaderHttpServletRequestWrapper(request);
//				visitLog.setRequestBody(new String(wrapper.getBody(), Charsets.UTF_8));
//			} catch (IOException e) {
//				e.printStackTrace();
//				logger.error("BodyReaderHttpServletRequestWrapper error:", e);
//			}
//		}
//		request.setAttribute("visitLogId", visitLog.getVisitLogId());
//		visitLogClient.send(visitLog);
		return wrapper;
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}


	@Override
	public void destroy() {

	}




}
