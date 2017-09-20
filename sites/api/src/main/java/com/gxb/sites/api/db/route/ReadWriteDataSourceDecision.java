/**
 *  Copyright (c)  2014-2020 Gaoxiaobang, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Gaoxiaobang, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Gaoxiaobang.
 */
package com.gxb.sites.api.db.route;


/**
 * read write sign holder.
 * 
 * 
 * @author lh
 * @date 2015年11月4日
 */
public class ReadWriteDataSourceDecision {
	public static final ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();
	
	public enum DataSourceType {
		write, read;
	}

	public static DataSourceType getDataSouce() {
		return holder.get();
	}
	
    public static void markWrite() {
        holder.set(DataSourceType.write);
    }
    
    public static void markRead() {
        holder.set(DataSourceType.read);
    }
    
    public static boolean isChoiceWrite() {
        return DataSourceType.write == holder.get();
    }
    
    public static boolean isChoiceRead() {
        return DataSourceType.read == holder.get();
    }
    
}
