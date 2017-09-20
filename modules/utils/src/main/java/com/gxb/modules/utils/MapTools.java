package com.gxb.modules.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gtli
 * @date 15/12/21
 */
public class MapTools {
    /**
     * 将value放入key对应的List中
     * @param resultMap
     * @param value
     * @param key
     * @param <T>
     * @param <K>
     */
    public static <T, K> void putValueIntoMap(Map<K, List<T>> resultMap, K key, T value) {
        if (resultMap.containsKey(key)) {
            List<T> courseQuestionRelatesInMap = resultMap.get(key);
            courseQuestionRelatesInMap.add(value);
        } else {
            List<T> courseQuestionRelatesInMap = new ArrayList<>();
            courseQuestionRelatesInMap.add(value);
            resultMap.put(key, courseQuestionRelatesInMap);
        }
    }

    /**
     * 将对象转换为Map
     * @param obj
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null)
            return null;

        return new org.apache.commons.beanutils.BeanMap(obj);
    }
}
