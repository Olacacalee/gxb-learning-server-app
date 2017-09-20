package com.gxb.modules.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016-10-21.
 */
public class PersonalityTools {

    public static Integer getPersonality(Map<Integer,Integer> map){
        Integer finalNum ;
        List<Integer> indexList = getMax(map);
        List<Integer> newKeyList = getNearMax(map,indexList,1);
        if(newKeyList.size()>1){
            finalNum = getFinalMaxIndex(newKeyList);
        }else{
            finalNum = newKeyList.get(0);
        }
        return finalNum;
    }

    public static Integer getFinalMaxIndex(List<Integer> indexList){
        int index = 10;
        for(Integer c:indexList){
            if(index>c){
                index = c;
            }
        }
        return index;
    }

    public static List<Integer> getNearMax(Map<Integer,Integer> map, List<Integer> indexList, Integer time){
        int total = -1;
        System.out.println(time);
        List<Integer> newKeyList = new ArrayList<>();
        for(int i=0;i<indexList.size();i++){
            int keyMini = indexList.get(i)-time;
            int keyPlus = indexList.get(i)+time;
            if(keyMini<1){
                keyMini = keyMini + 9;
            }
            if(keyPlus>9){
                keyPlus = keyPlus-9;
            }
            if(total<(map.get(keyMini)+map.get(keyPlus))){
                total = map.get(keyMini)+map.get(keyPlus);
            }
        }
        for(int i=0;i<indexList.size();i++){
            int keyMini = indexList.get(i)-time;
            int keyPlus = indexList.get(i)+time;
            if(keyMini<1){
                keyMini = keyMini + 9;
            }
            if(keyPlus>9){
                keyPlus = keyPlus-9;
            }
            if(total==(map.get(keyMini)+map.get(keyPlus))){
                newKeyList.add(indexList.get(i));
            }
        }
        if(time>=4){
            return newKeyList;
        }
        if(newKeyList.size()>1){
            time ++;
            return getNearMax(map,newKeyList,time);
        }else{
            return newKeyList;
        }
    }

    public static List<Integer> getMax(Map<Integer,Integer> map){
        Integer start = -1;
        Set<Integer> iterator = map.keySet();
        List<Integer> indexList = new ArrayList<>();
        for(Integer key:iterator){
            if(start<map.get(key)){
                start = map.get(key);
            }
        }
        for(Integer key:iterator){
            if(start == map.get(key)){
                indexList.add(key);
            }
        }
        return indexList;
    }
}
