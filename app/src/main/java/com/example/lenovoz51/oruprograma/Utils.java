package com.example.lenovoz51.oruprograma;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class Utils {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";
    public static final String LOGIN_URL = "&units=metric&appid=07ea1ba79a211b3d8a39201edc6f771b";

    public static JSONObject getObject(String name, JSONObject jsonObject) throws JSONException{
        JSONObject jobject = jsonObject.getJSONObject(name);
        return jobject;
    }

    public static String getString(String name, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(name);
    }

    public static float getfloat(String name, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(name);
    }

    public static double getdouble(String name, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(name);
    }

    public static int getint(String name, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(name);
    }

}
