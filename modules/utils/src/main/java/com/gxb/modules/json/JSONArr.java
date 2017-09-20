package com.gxb.modules.json;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.gxb.modules.utils.ExceptionTools;


/**
 * time : 15/8/4.
 * auth : bqxu
 * desc :
 * tips :
 * 1.
 */
public class JSONArr {

    private JSONArray jsonArray;

    public JSONArr() {
    }

    public JSONArr(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public static JSONArr fromObject(String obj) {
        JSONArr jsonArr = new JSONArr();
        if (obj == null) {
            return jsonArr;
        }
        try {
            JSONArray jsonArray = JSONArray.parseArray(obj);
            jsonArr.setJsonArray(jsonArray);
        } catch (JSONException e) {
            ExceptionTools.unchecked(e);
        }
        return jsonArr;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public int size() {
        if (jsonArray == null) {
            return 0;
        }
        return jsonArray.size();
    }

    public JSONObj getJSONObject(int index) {
        if (jsonArray == null) {
            return new JSONObj();
        }
        try {
            return new JSONObj(jsonArray.getJSONObject(index));
        } catch (JSONException e) {
            ExceptionTools.unchecked(e);
        }
        return new JSONObj();

    }

    public String getString(int index) {
        return getString(index, "");
    }

    public String getString(int index, String def) {
        if (jsonArray == null) {
            return def;
        }
        try {
            return jsonArray.getString(index);
        } catch (JSONException e) {
            ExceptionTools.unchecked(e);
        }
        return def;
    }


}
