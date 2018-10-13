package com.example.lenovoz51.oruprograma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class WeatherHttpClient {
    Weather weather;
    public Weather getWeatherData (String place) {

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Utils.BASE_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                Weather weather = new Weather();
                try {
                    Location location = new Location();
                    JSONObject coordObj = Utils.getObject("coord", jsonObject);
                    location.setLan(Utils.getfloat("lat", coordObj));
                    location.setLon(Utils.getfloat("lon", coordObj));

                    JSONObject sysobj = Utils.getObject("sys", jsonObject);
                    location.setCountry(Utils.getString("country", sysobj));
                    location.setLastUpdate(Utils.getint("dt", jsonObject));
                    location.setSunrise(Utils.getint("sunrise", sysobj));
                    location.setSunset(Utils.getint("sunset", sysobj));
                    location.setCity(Utils.getString("name", jsonObject));
                    weather.location = location;

                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject weatObj = jsonArray.getJSONObject(0);
                    weather.currentCondition.setWeatherId(Utils.getint("id", weatObj));
                    weather.currentCondition.setDescription(Utils.getString("description", weatObj));
                    weather.currentCondition.setCondition(Utils.getString("main", weatObj));
                    weather.currentCondition.setIcon(Utils.getString("icon", weatObj));

                    JSONObject windobj = Utils.getObject("wind", jsonObject);
                    weather.wind.setSpeed(Utils.getfloat("speed", windobj));
                    weather.wind.setDeg(Utils.getfloat("deg", windobj));

                    JSONObject cloudobj = Utils.getObject("clouds", jsonObject);
                    weather.clouds.setPrecipitation(Utils.getint("all", cloudobj));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppControler.getmInstance().addToRequestQueue(jsonArrayRequest);

        return weather;
    }
       /* HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line+"\r\n");
            }

            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

}
