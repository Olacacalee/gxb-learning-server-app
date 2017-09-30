package com.gxb.sites.api.sys.auth;

import com.gxb.modules.domain.auth.AuthToken;
import com.gxb.modules.domain.tenant.Tenant;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * time : 15/10/22.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Component
public class UserContent implements Serializable {

    private static final long serialVersionUID = -5662131076635716070L;

    public static final ThreadLocal<AuthToken> userThreadLocal = new ThreadLocal<>();

    public static final ThreadLocal<Tenant> tenantThreadLocal = new ThreadLocal<>();

    public static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    public static final ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前的request对象
     *
     * @return
     */
    public static final HttpServletRequest getRequest() {
        return requestThreadLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestThreadLocal.set(request);
    }

    /**
     * 获取当前response对象
     *
     * @return
     */
    public static final HttpServletResponse getResponse() {
        return responseThreadLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseThreadLocal.set(response);
    }

    public static void setUser(AuthToken authToken) {
        if (null != authToken) {
            userThreadLocal.set(authToken);
        }
    }

    public static AuthToken getUser() {
        return userThreadLocal.get();
    }

    public static void setTenant(Tenant tenant) {
        if (null != tenant) {
            tenantThreadLocal.set(tenant);
        }
    }

    public static Tenant getTenant() {
        return tenantThreadLocal.get();
    }

    /**
     * 清除登录上下文环境
     */
    public static void clear() {
        userThreadLocal.set(null);
        requestThreadLocal.set(null);
        responseThreadLocal.set(null);
    }

}
