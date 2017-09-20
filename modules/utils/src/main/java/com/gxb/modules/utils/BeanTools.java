/**
 * Copyright (c)  2014-2020 Gaoxiaobang, Inc.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of Gaoxiaobang,
 * Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Gaoxiaobang.
 */
package com.gxb.modules.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @author lh
 * @date 2015年10月29日
 */
public class BeanTools {


    public static void populate(Object bean, Map<String, ? extends Object> properties) {
        try {
            BeanUtils.populate(bean, properties);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ExceptionTools.unchecked(e);
        }
    }


    public static void copyProperties(Object to, Object from) {
        try {
            BeanUtils.copyProperties(to, from);
        } catch (ConversionException | IllegalAccessException | InvocationTargetException e) {
            ExceptionTools.unchecked(e);
        }
    }

    public static void copyProperty(Object bean, String name, Object value) {
        try {
            BeanUtils.copyProperty(bean, name, value);
        } catch (ConversionException | IllegalAccessException | InvocationTargetException  e) {
            ExceptionTools.unchecked(e);
        }
    }

    public static void setProperty(Object obj, String name, Object value) {
        try {
            BeanUtils.setProperty(obj, name, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ExceptionTools.unchecked(e);
        }
    }

    public static String getProperty(Object obj, String name) {
        try {
            return BeanUtils.getProperty(obj, name);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            ExceptionTools.unchecked(e);
        }
        return null;
    }

    public static boolean equalProperty(Object obj, String name, Object value) {
        return StringTools.getString(value).equals(getProperty(obj, name));
    }
    
    public static void copyNotNullProperties(Object to, Object from) {
		
    	PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(to);
		try {
			for (PropertyDescriptor descriptor : propertyDescriptors) {
				
				Method write = PropertyUtils.getWriteMethod(descriptor);
				Method read = PropertyUtils.getReadMethod(descriptor);
				if(write == null || read == null){
					continue;
				}

				String property = descriptor.getName();

                try {
                    Object value = PropertyUtils.getProperty(from, property);
                    if (value != null) {
                        PropertyUtils.setProperty(to, property, value);
                    }
                }catch (NoSuchMethodException e){

                }

			}
		} catch (InvocationTargetException | IllegalAccessException e) {
			ExceptionTools.unchecked(e);
		}
	}

}
