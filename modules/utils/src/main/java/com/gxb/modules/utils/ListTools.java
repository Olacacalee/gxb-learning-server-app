package com.gxb.modules.utils;

import java.util.List;

/**
 * time : 15/11/2.
 * auth :
 * desc :
 * tips :
 * 1.
 */
public class ListTools<T> {

    public static <T> T find(List<T> list, String key, Object value) {
        for (T obj : list) {
            if (BeanTools.equalProperty(obj, key, value)) {
                return obj;
            }
        }
        return null;
    }
}
