package com.gxb.sites.api.sys;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

/**
 * time : 15/6/13.
 * auth : bqxu
 * desc :
 * tips :
 * 1.
 */
public class ApplicationHelper implements ApplicationContextAware, DisposableBean {


    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(ApplicationHelper.class);

    private static ServletContext servletContext;
    private static String contextPath;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        logger.error("ApplicationContext",context);
        applicationContext = context;
    }

    @Override
    public void destroy() throws Exception {
        clear();
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除IApplicationHelper中的ApplicationContext为Null.
     */
    public static void clear() {
        logger.debug("清除IApplicationHelper中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    public static String getWebRoot() {
        try {
            return applicationContext.getResource("/").getFile().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Properties getProperties(String pathName) {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = applicationContext.getResource(pathName).getInputStream();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return properties;
    }

    public static String getMessage(String var1, Locale var4) {
        return applicationContext.getMessage(var1, null, var1, var4);
    }

    public static String getContextPath() {
        if (contextPath != null) {
            return contextPath;
        }

        contextPath = servletContext.getContextPath();

        return contextPath;
    }

    /**
     * 获取Servlet上下文
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static void setServletContext(ServletContext sc) {
        servletContext = sc;
    }

}
