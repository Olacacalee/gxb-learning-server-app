package com.gxb.modules.core.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 性能记录
 * 
 * @date 2012-7-3
 * @author lh
 */
public class PerformanceMonitorInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(PerformanceMonitorInterceptor.class);
	private ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		StopWatch stopWatch = new StopWatch(handler.toString());
		stopWatchLocal.set(stopWatch);
		stopWatch.start(handler.toString());

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		StopWatch stopWatch = stopWatchLocal.get();
		if((StopWatch)null==stopWatch){return;}
		stopWatch.stop();
		String currentPath = request.getRequestURI();
		String queryString = request.getQueryString();
		queryString = queryString == null ? "" : "?" + queryString;
		
		logger.debug("Access URL Path:" + currentPath + queryString + " | Method:" + request.getMethod() + " | Time:" + stopWatch.getTotalTimeMillis() + "ms");
		stopWatchLocal.set(null);
	}
}
