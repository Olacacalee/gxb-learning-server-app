package com.gxb.sites.api.HelloWorld.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
@Service
public class HelloWorldService {


    public List test(){
        List list =  new ArrayList();
        list.add("abjdjfj");
        list.add("iowjfoejf");
        return list;
    }

    public String ChartArraySort() {
        String answer = "A,C,B,D";
        char[] ch = answer.toCharArray();
        java.util.Arrays.sort(ch);
        answer = String.valueOf(ch);
        return answer;
    }

}
