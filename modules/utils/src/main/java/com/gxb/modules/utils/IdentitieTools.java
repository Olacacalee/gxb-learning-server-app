package com.gxb.modules.utils;

import com.gxb.modules.json.JSONArr;
import com.gxb.modules.json.JSONObj;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author calvin
 */
public class IdentitieTools {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodeTools.encodeBase62(randomBytes);
	}

	/**
	 * 根据类型随机获取该类型url
	 * @param map
	 * @param type
	 * @return
	 */
	public static String getUrlByType(Map map, String type) {
		if(StringTools.isBlank(type) || map == null){
			return getSystemConfigMap(type);
		}
		List<String> stringList = (List<String>) map.get(type);
		if(CollectionTools.isEmpty(stringList)){
			return getSystemConfigMap(type);
		}
		return stringList.get(random.nextInt(stringList.size()));
	}


	public static String getSystemConfigMap(String type){
		String string;
		try {
			string = HttpClientTools.get("https://gxb-api.gaoxiaobang.com/gxb-api/systemConfig/reload/str");
			JSONObj jsonObj = JSONObj.fromObject(string);
			JSONArr jsonArr = jsonObj.getJSONArray(type);
			if(jsonArr.size() > 0){
				return jsonArr.getString(random.nextInt(jsonArr.size()));
			} else {
				return getConfigStr(type);
			}
		} catch (Exception e) {
			return getConfigStr(type);
		}
	}

	public static String getConfigStr(String type){

		String string;
		if(type.startsWith("dir")){
			string = "/gxb-" + type.replace("dir", "");
		} else {
			string = "https://gxb-" + type + ".oss-cn-beijing.aliyuncs.com";
		}
		return string;
	}


}
