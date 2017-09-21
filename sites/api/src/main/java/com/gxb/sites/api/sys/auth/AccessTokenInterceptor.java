package com.gxb.sites.api.sys.auth;

import com.gxb.sites.api.auth.web.controller.AuthTokenCtl;
import com.gxb.modules.domain.auth.AuthToken;
import com.gxb.modules.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * time : 15/11/5.
 * auth :
 * desc :
 * tips :
 * 1.
 */
public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private AccessTokenService accessTokenService;
    @Autowired
    private AuthTokenCtl authTokenService;

    public AccessTokenInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String access_token = request.getParameter("access_token");
        if (access_token == null && request.getAttribute("access_token") != null) {
            access_token = (String) request.getAttribute("access_token");
        }
        if (handler instanceof HandlerMethod) {
            AccessTokenCheck accessTokenCheck;
            HandlerMethod methodHandler = (HandlerMethod) handler;
            accessTokenCheck = methodHandler.getMethodAnnotation(AccessTokenCheck.class);
            if (accessTokenCheck == null) {
                Object bean = methodHandler.getBean();
                accessTokenCheck = bean.getClass().getAnnotation(AccessTokenCheck.class);
            }
            if (accessTokenCheck != null && !accessTokenCheck.value()) {
                return true;
            } else {
                if (access_token == null) {
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.write("{\"status\":400,\"message\":\"请填写token\"}");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                    return false;
                }
                AuthToken authToken = authTokenService.token(access_token);//accessTokenService.checkToken(access_token, "app");
                if (StringTools.isNotBlank(StringTools.getString(authToken.getUserId()))) {
                    request.setAttribute("accessToken", authToken);
                    UserContent.setUser(authToken);
                    return true;
                } else {
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.write("{\"status\":400,\"message\":\"token失效\"}");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
