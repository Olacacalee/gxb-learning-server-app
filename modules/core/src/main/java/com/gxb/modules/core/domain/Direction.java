package com.gxb.modules.core.domain;

/**
 * 
 * @author lh
 * @date 2015年6月17日
 */
public enum Direction {
	ASC, DESC;
	
	public static Direction getString(String enumValue){
		Direction direction = Direction.DESC;
		try {
			direction = Direction.valueOf(enumValue.toUpperCase());
		} catch (Exception e) {
			//return default
		}
		return direction;
	}
	
}
