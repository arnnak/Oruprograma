package com.example.lenovoz51.oruprograma;

import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class JsonWeatherParser {

    public static Weather getWeather(String data ){
        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(data);
            Location location = new Location();
            JSONObject coordObj = Utils.getObject("coord",jsonObject);
            location.setLan(Utils.getfloat("lat",coordObj));
            location.setLon(Utils.getfloat("lon",coordObj));

            JSONObject sysobj = Utils.getObject("sys",jsonObject);
            location.setCountry(Utils.getString("country",sysobj));
            location.setLastUpdate(Utils.getint("dt",jsonObject));
            location.setSunrise(Utils.getint("sunrise",sysobj));
            location.setSunset(Utils.getint("sunset",sysobj));
            location.setCity(Utils.getString("name",jsonObject));
            weather.location = location;

            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject weatObj = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getint("id",weatObj));
            weather.currentCondition.setDescription(Utils.getString("decription",weatObj));
            weather.currentCondition.setCondition(Utils.getString("main",weatObj));
            weather.currentCondition.setIcon(Utils.getString("icon",weatObj));

            JSONObject windobj = Utils.getObject("wind",jsonObject);
            weather.wind.setSpeed(Utils.getfloat("speed",windobj));
            weather.wind.setDeg(Utils.getfloat("deg", windobj));

            JSONObject cloudobj = Utils.getObject("clouds",jsonObject);
            weather.clouds.setPrecipitation(Utils.getint("all",cloudobj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
