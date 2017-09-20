package com.gxb.sites.api.web.handler;

import java.lang.annotation.*;

/**
 * time : 15/11/17.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogCheck {
    String operate() default "true";
    String visit() default "true";
}
