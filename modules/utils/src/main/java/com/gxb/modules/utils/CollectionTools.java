/**
 *  Copyright (c)  2014-2020 Gaoxiaobang, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Gaoxiaobang, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Gaoxiaobang.
 */
package com.gxb.modules.utils;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionTools {

	public static boolean isEmpty(final Object[] array) {
		return ArrayUtils.isEmpty(array);
	}
	
	public static boolean isEmpty(Collection<?> coll) {
        return CollectionUtils.isEmpty(coll);
    }
	
	public static boolean isNotEmpty(Collection<?> coll) {
        return !CollectionUtils.isEmpty(coll);
    }

	public static <T> void sort(List<T> list, String property, boolean asc) {
		Comparator<?> comparator = ComparableComparator.getInstance();
		comparator = ComparatorUtils.nullLowComparator(comparator);
		if (!asc) {
			comparator = ComparatorUtils.reversedComparator(comparator);
		}
		Collections.sort(list, new BeanComparator(property, comparator));
	}

	public static <T> List<T> page(List<T> list, Integer curPage, Integer pageSize) {
		if(curPage <1 || pageSize <=0)
			return null;
		int startIndex = (curPage-1) * pageSize;
		int endIndex = startIndex +pageSize;
		if(startIndex <= list.size()-1){
			return list.subList(startIndex,endIndex>list.size()?list.size():endIndex);
		}
		return null;
	}



	
}
