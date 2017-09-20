package com.gxb.modules.json;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.gxb.modules.utils.BeanTools;
import com.gxb.modules.utils.ExceptionTools;

/**
 * time : 15/8/4.
 * auth :bqxu
 * desc :
 * tips :
 * 1.
 */
public class JSONObj {


    private JSONObject jsonObject = null;

    public JSONObj() {
    }

    public JSONObj(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    public static String toJSONStr(Object object) {
        Object obj = JSONObject.toJSON(object);
        if (obj == null) {
            return "{}";
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).toJSONString();
        } else if (obj instanceof JSONArray) {
            return ((JSONArray) obj).toJSONString();
        }
        return "{}";
    }

    public static JSONObj fromObject(String testAim) {
        JSONObj jsonObj = new JSONObj();
        if (testAim == null) {
            return jsonObj;
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(testAim);
            jsonObj.setJsonObject(jsonObject);
        } catch (JSONException e) {
            ExceptionTools.unchecked(e);
        }
        return jsonObj;
    }

    public <T> T toBean(Class<T> optionClass) {
        try {
            return JSONObject.toJavaObject(jsonObject, optionClass);
        } catch (JSONException e) {
            ExceptionTools.unchecked(e);
        }
        return null;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(String name) {
        return getString(name, null);
    }

    public String getString(String name, String s) {
        if (jsonObject == null || !jsonObject.containsKey(name)) {
            return s;
        }
        return jsonObject.getString(name);
    }

    public boolean has(String key) {
        if (jsonObject == null) {
            return false;
        }
        return jsonObject.containsKey(key);
    }

    public JSONArr getJSONArray(String arrKey) {
        if (jsonObject == null) {
            return new JSONArr();
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(arrKey);
            return new JSONArr(jsonArray);
        } catch (JSONException e) {
            ExceptionTools.unchecked(e);
        }
        return new JSONArr();
    }

    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    public Double getDouble(String key, Double def) {
        if (jsonObject == null) {
            return def;
        }
        if (jsonObject.containsKey(key)) {
            try {
                return jsonObject.getDouble(key);
            } catch (JSONException e) {
                ExceptionTools.unchecked(e);
            }
        }
        return def;

    }

    public JSONObj getJsonObject(String key) {
        if (jsonObject == null || !jsonObject.containsKey(key)) {
            return null;
        }
        try {
            JSONObject jsonObj = jsonObject.getJSONObject(key);
            return new JSONObj(jsonObj);
        } catch (JSONException e) {
            ExceptionTools.unchecked(e);
        }
        return null;
    }

    public String toString() {
        return jsonObject.toString();
    }

    public <T> void copyPropertiesTo(T proInfo) {
        if (jsonObject != null) {
            BeanTools.copyProperties(proInfo, jsonObject);
        }
    }


    public void put(String key, Object value) {
        if (jsonObject != null) {
            jsonObject.put(key, value);
        }
    }

    public Long getLong(String key) {
        if (jsonObject != null) {
            if (jsonObject.containsKey(key)) {
                return jsonObject.getLong(key);
            }
        }
        return null;
    }
}
