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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lh
 * @date 2015年10月21日
 */
public class ValidatorTools {
    private static final String NAME_PATTERN_EN = "^[A-Za-z\\.\\- ]+$";
    private static final String NAME_PATTERN_CN = "^[\\u3400-\\u4db5\\u4e00-\\u9fbb\\ue815-\\ue864\\uf92c-\\ufa29\\u00b7\\u2022\\.]+$";

    private static final String MOBILE_PATTERN = "^\\s*\\+?\\s*(\\(\\s*\\d+\\s*\\)|\\d+)(\\s*-?\\s*(\\(\\s*\\d+\\s*\\)|\\s*\\d+\\s*))*\\s*$";
    private static final String TELEPHONE_PATTERN = "0\\d{2,3}-\\d{7,8}";

    public static boolean checkUserName(String userName) {
        if (StringTools.isEmpty(userName)) {
            return false;
        }
        Pattern enNamePattern = Pattern.compile(NAME_PATTERN_EN);
        Pattern chNamePattern = Pattern.compile(NAME_PATTERN_CN);

        Matcher matcher;

        matcher = chNamePattern.matcher(userName);
        if (matcher.matches()) {
            return true;
        }

        matcher = enNamePattern.matcher(userName);
        if (matcher.matches()) {
            return true;
        }

        return false;
    }


    /**
     * 判断手机号
     *
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile(MOBILE_PATTERN);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断电话
     *
     * @return
     */
    public static boolean isTelephone(String phonenumber) {
        String phone = TELEPHONE_PATTERN;
        Pattern p = Pattern.compile(phone);
        Matcher m = p.matcher(phonenumber);
        return m.matches();
    }


    public static boolean isIp(String url) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
