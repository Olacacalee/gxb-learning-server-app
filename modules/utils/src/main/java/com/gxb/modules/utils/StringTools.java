package com.gxb.modules.utils;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串实用工具类
 */
public class StringTools {
    public static final String EMPTY = "";

    /**
     * 判断一个字符串对象是否是""或null
     *
     * @param str 要判断的字符串对象
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isEmpty(Object obj) {
        boolean flag = false;
        if (obj == null) {
            flag = true;
        }
        return flag;
    }

    public static boolean isNotEmpty(Object obj) {
        boolean flag = false;
        if (obj != null) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断一个字符串对象是否不是""或null
     *
     * @param str 要判断的字符串对象
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    /**
     * 将一个字符串的前后空白去掉，如果为null转换成""
     *
     * @param str 要操作的字符串对象
     * @return
     */
    public static String trimToEmpty(String str) {
        return StringUtils.trimToEmpty(str);
    }

    /**
     * 将一个字符串的前后空白去掉，如果为""转换成null
     *
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        return StringUtils.trimToNull(str);
    }

    /**
     * 用指定的分割字符连接数组元素
     *
     * @param array     对象数组
     * @param separator 分割字符
     * @return
     */
    public static String join(Object[] array, String separator) {
        return StringUtils.join(array, separator);
    }

    /**
     * 用指定的分割字符连接数组元素
     *
     * @param iterable 对象数组
     * @param separator  分割字符
     * @return
     */
    public static String join(Iterable<?> iterable, String separator) {
        return StringUtils.join(iterable, separator);
    }

    /**
     * 获取文件路径中文件扩展名，不含.
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFileExtName(String filePath) {
        filePath = filePath.replace('\\', '/');
        int pos = filePath.lastIndexOf('/');
        if (pos > 0) {
            filePath = filePath.substring(pos + 1, filePath.length());
        }
        pos = filePath.lastIndexOf('.');
        String baseName = "";

        if (pos > 0) {
            baseName = filePath.substring(pos + 1, filePath.length());
        }
        return baseName;
    }

    public static String substringBeforeLast(String str, String separator) {
        return StringUtils.substringBeforeLast(str, separator);
    }


    /**
     * 字符串转成Long
     *
     * @param num ：要转化的数字字符串
     * @param def ：默认值
     * @return 返回 Long
     */
    public static Long getLong(String num, long def) {
        if (isEmpty(num)) {
            return def;
        }
        long ret = def;
        int i = num.indexOf(".");
        if (i == 0) {
            num = "0";
        } else if (i > 0) {
            num = num.substring(0, i);
        }
        try {
            ret = Long.parseLong(num);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * 字符串转成Long
     *
     * @param num ：要转化的数字字符串
     * @return 返回 Long
     */
    public static Long getLong(String num) {
        return getLong(num, 0);
    }

    public static Long getLong(Object num) {
        return getLong(getString(num));
    }

    public static Long getLong(Object num, long def) {
        return getLong(getString(num), def);
    }

    /**
     * 字符串转成Integer
     *
     * @param num ：要转化的数字字符串
     * @param def ：默认值
     * @return 返回 Integer
     */
    public static Integer getInteger(String num, int def) {
        if (isEmpty(num)) {
            return def;
        }
        int ret = def;
        try {
            ret = Integer.valueOf(num);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * 字符串转成Integer
     *
     * @param num ：要转化的数字字符串
     * @return 返回 Integer
     */
    public static Integer getInteger(String num) {
        if (isEmpty(num)) {
            return null;
        }
        int ret = 0;
        int i = num.indexOf(".");
        if (i == 0) {
            num = "0";
        } else if (i > 1) {
            num = num.substring(0, i);
        }
        try {
            ret = Integer.valueOf(num);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static Integer getInteger(Object num) {
        return getInteger(getString(num));
    }

    public static Integer getInteger(Object num, int def) {
        return getInteger(getString(num), def);
    }

    /**
     * 替换字符
     *
     * @param str
     * @param searchChar
     * @param replaceChar
     * @return
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        return StringUtils.replaceChars(str, searchChar, replaceChar);
    }

    /**
     * 替换字符串
     *
     * @param str
     * @param searchString
     * @param replaceString
     * @return
     */
    public static String replace(String str, String searchString, String replaceString) {
        return StringUtils.replace(str, searchString, replaceString);
    }

    /**
     * if the input string is empty, then return the default string
     *
     * @param obj
     * @param def
     * @return string
     */
    public static String getString(Object obj, String def) {
        if (obj == null) {
            return def;
        }
        String result = obj.toString();
        return result.length() == 0 ? def : result;
    }

    public static String getString(Object obj) {
        return getString(obj, "");
    }

    public static String replaceFileSep(String sep) {
        if (File.separator.equals("\\")) {
            return StringTools.replace(sep, "/", "\\");
        } else {
            return StringTools.replace(sep, "\\", "/");
        }
    }

    public static String replaceUrlSep(String url) {
        return StringTools.replace(url, "\\", "/");
    }

    public static String escapeHtml(String text) {
        String temp = StringTools.replace(text, "&", "&amp;");

        temp = StringTools.replace(temp, " ", "&nbsp;");
        temp = StringTools.replace(temp, ">", "&gt;");
        temp = StringTools.replace(temp, "<", "&lt;");
        temp = StringTools.replace(temp, "\"", "&quot;");
        temp = StringTools.replace(temp, "\n", "<br>");
        temp = StringTools.replace(temp, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");

        return temp;
    }

    public static String stripUnsafeTag(String text) {
        String temp = text.replaceAll("<script[^>]*>([^<]*)</script>", "");
        return temp;
    }

    public static boolean checkYearMonth(String validYear) {
        Pattern p = Pattern.compile("^\\d{4}((0\\d)|(1[0-2])|([0-9]))$");
        Matcher m = p.matcher(validYear);
        return m.matches();
    }

    public static String formatMillsToString(Long mills) {
        if (StringTools.isEmpty(mills)) {
            return "00:00:00";
        }
        long ss = (mills / 1000) % 60;
        long mm = (mills / 60000) % 60;
        long hh = mills / 3600000;
        return getFormatString(hh) + ":" + getFormatString(mm) + ":" + getFormatString(ss);
    }

    public static String formatMillsToString2(Long mills) {
        if (StringTools.isEmpty(mills)) {
            return "00:00";
        }
        long ss = (mills / 1000) % 60;
        long mm = mills / 60000;
        return getFormatString(mm) + ":" + getFormatString(ss);
    }

    public static String getFormatString(long time) {
        if (time < 10l) {
            return "0" + time;
        }
        return time + "";
    }

    public static String clearEmptyLine(String in) {
        Pattern pattern = Pattern.compile("\n");
        Matcher matcher = pattern.matcher(in);
        if (matcher.matches()) {
            return matcher.replaceAll("\n");
        }
        return in;
    }

    public static String integerToString(Integer obj) {
        if (obj == null) {
            return null;
        }
        if (obj < 10) {
            return "0" + obj;
        }
        return obj + "";
    }

    /**
     * 位数不足，前面补0
     *
     * @param code
     * @param len
     * @return
     */
    public static String codeAddOne(String code, int len) {
        Integer intOrder = Integer.parseInt(code);
        String strNo = intOrder.toString();
        while (strNo.length() < len) {
            strNo = "0" + strNo;
        }
        return strNo;
    }

    /**
     * 16进制转十进制
     *
     * @param hex ：
     * @return ：
     */
    public static Integer hexToDec(String hex) {
        for (int i = 0; i < hex.length(); i++) {
            if (!((hex.charAt(i) >= 48 && hex.charAt(i) <= 57) || (hex.charAt(i) >= 'a' && hex.charAt(i) <= 'f') || (hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F'))) {
                return 0;
            }
        }
        return Integer.valueOf(hex, 16);
    }

    /**
     * 十进制转16进制
     *
     * @param num ：
     * @return ：
     */
    public static String decToHex(Integer num) {
        if (num == null) {
            return "0";
        }
        return Integer.toHexString(num);
    }

    /**
     * 判断字符是否为
     *
     * @param text
     * @return
     */
    public static boolean isEmptyOrNullString(String text) {
        if (isEmpty(text)) return true;

        if ("null".equals(text) || "(null)".equals(text)) return true;

        return false;
    }

    public static String[] convert2Array(String text, String split) {
        if (StringTools.isEmpty(text)) {
            return null;
        }
        String[] strArray = null;
        if (text.indexOf(split) > -1) {
            strArray = text.split(split);
        } else {
            strArray = new String[]{text};
        }
        return strArray;
    }

    public static String[] convert2Array(String text) {
        return convert2Array(text, ",");
    }

    public static Integer[] convert2IntegerArray(String text, String split) throws NumberFormatException {
        String[] strArr = convert2Array(text, split);
        if (strArr == null) {
            return null;
        }
        Integer[] intArr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.valueOf(strArr[i]);
        }
        return intArr;

    }

    public static Integer[] convert2IntegerArray(String text) throws NumberFormatException {
        return convert2IntegerArray(text, ",");
    }

    public static List<String> convert2StringList(String text, String split) throws NumberFormatException {
        List<String> strList = new ArrayList<String>();
        String[] strArr = convert2Array(text, split);
        for (String s : strArr) {
            strList.add(s);
        }
        return strList;
    }

    public static List<String> convert2StringList(String text) {
        return convert2StringList(text, ",");
    }

    public static List<Long> convert2LongList(String text, String split) throws NumberFormatException {
        List<Long> lList = new ArrayList<Long>();
        String[] strArr = convert2Array(text, split);
        if (StringTools.isEmpty(strArr)) {
            return null;
        }
        for (String s : strArr) {
            Long temp = Long.valueOf(s);
            lList.add(temp);
        }
        return lList;
    }

    public static List<Long> convert2LongList(String text) throws NumberFormatException {
        return convert2LongList(text, ",");
    }

    public static List<Integer> convert2IntegerList(String text, String split) throws NumberFormatException {
        List<Integer> iList = new ArrayList<Integer>();
        String[] strArr = convert2Array(text, split);
        if (StringTools.isEmpty(strArr)) {
            return null;
        }
        for (String s : strArr) {
            Integer temp = Integer.valueOf(s);
            iList.add(temp);
        }
        return iList;
    }

    public static List<Integer> convert2IntegerList(String text) throws NumberFormatException {
        return convert2IntegerList(text, ",");
    }

    public static String concat(String symbol, String... param) {
        if (param == null) {
            return null;
        }
        String result = "";
        String[] params = param;
        for (int i = 0; i < params.length; i++) {
            result = i == 0 ? params[i] : result + symbol + params[i];
        }
        return result;
    }

    public static String[] split(final String str, final String separatorChars) {
        return StringUtils.split(str, separatorChars);
    }

    public static Map<String, Object> split2map(final String paramStr) {
        return split2map(",", ":", paramStr);
    }

    /**
     * 将字符串根据两个分隔符分割为Map
     *
     * @author lh
     * @date 2015年10月26日
     */
    public static Map<String, Object> split2map(final String separatorChar, final String separatorChar2, final String paramStr) {
        if (StringTools.isBlank(paramStr)) return null;

        String[] splitList = StringUtils.split(paramStr, ",");
        if (CollectionTools.isEmpty(splitList)) return null;

        Map<String, Object> rsMap = new HashMap<String, Object>(splitList.length, 1);

        for (String str : splitList) {
            String[] p = StringUtils.split(str, ":", 2);
            if (CollectionTools.isEmpty(p) || p.length < 2) continue;

            rsMap.put(p[0].trim(), p[1].trim());
        }

        return rsMap;
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 比较两个字符串，如果其中一个字符串为blank，计算不相等
     *
     * @param arg1
     * @param arg2
     * @return
     * @author gtli
     */
    public static boolean equalsString(String arg1, String arg2) {
        if (isBlank(arg1) || isBlank(arg2)) {
            return false;
        }
        return arg1.equals(arg2);
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     * @author sunninghai
     * @date 2015年11月5日
     */
    public static String camelToUnderScore(String str) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }


    /**
     *
     */
    public static String getHost(String url) {
        if (url.indexOf("://") > 0) {
            url = url.substring(url.indexOf("://")+3, url.length());
        }
        if (url.indexOf("/") > 0) {
            url = url.substring(0, url.indexOf("/"));
        }
        if (url.indexOf(":") > 0) {
            url = url.substring(0, url.indexOf(":"));
        }
        return url;
    }

    public static String getShotName(String url) {
        if(!url.contains(".")){
            return null;
        }
        if (url.lastIndexOf(".") > 0) {
            url = url.substring(0, url.lastIndexOf("."));
        }
        if (url.lastIndexOf(".") > 0) {
            url = url.substring(0, url.lastIndexOf("."));
        }
        if (url.indexOf(".") > 0) {
            url = url.substring(url.indexOf(".")+1, url.length());
        }
        return url;
    }

	public static String getShortName(String url) {
		String shortName = null;
		int index = url.indexOf(".");
		if (!url.contains(".") || index == 0) {
			return null;
		}
		if (index > 0) {
			shortName = url.substring(0, index);
		}
		return shortName;
	}

	public static String getFirstDomain(String url) {
		Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		matcher.find();
		return matcher.group();
	}

    public static boolean isEmail(String email) {

        Pattern regex = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)*$");
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^\\s*\\+?\\s*(\\(\\s*\\d+\\s*\\)|\\d+)(\\s*-?\\s*(\\(\\s*\\d+\\s*\\)|\\s*\\d+\\s*))*\\s*$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    public static Integer long2Integer(Long l) {
        if(l==null){
            return null;
        }
        return Integer.parseInt((new Long(l.toString())).toString());

    }

    public static Long string2Long(Object s) {
        if(s==null || s.equals("")){
            return null;
        }
        return new Long(s.toString());

    }

    public static Long object2Long(Object s) {
        if(s==null || s.equals("")){
            return null;
        }
        return new Long(s.toString());

    }

    public static Integer object2Integer(Object s) {
        if(s==null || s.equals("")){
            return null;
        }
        return new Integer(s.toString());

    }

    public static String object2String(Object s) {
        if(s==null || s.equals("")){
            return null;
        }
        return new String(s.toString());

    }

    public static String concatUrl(String prefixbucket,String relativeUrl){
        if(isEmpty(prefixbucket) || isEmpty(relativeUrl)){
            return prefixbucket;
        }else if(relativeUrl.startsWith("http:")){
            //存在http://
            return relativeUrl;
        }else if(!prefixbucket.endsWith("/") && !relativeUrl.startsWith("/")){
            //中间没有/分割自动添加
            return prefixbucket.concat("/").concat(relativeUrl);
        }else if(prefixbucket.endsWith("/") && relativeUrl.startsWith("/")){
            //两头都有/去掉一个
            prefixbucket = substringBeforeLast(prefixbucket,"/");
            return prefixbucket.concat(relativeUrl);
        }else{
            return prefixbucket.concat(relativeUrl);
        }
    }



}
