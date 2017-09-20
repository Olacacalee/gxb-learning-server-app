package com.gxb.modules.utils;

/**
 * Created by He on 2016/10/24.
 */
public class CharArraySort {
    public static String CharArraySort(String useranswer){
        String newanswer="";
        char[] ch = useranswer.toCharArray();
        for (int i = ch.length - 1; i >= 0; i--) {
            newanswer += ch[i];
        }

        return newanswer;
    }
}
