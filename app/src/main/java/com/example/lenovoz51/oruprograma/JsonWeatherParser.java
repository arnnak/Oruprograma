package com.example.lenovoz51.oruprograma;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class JsonWeatherParser extends AppCompatActivity {

    private TextView cityname;
    private ImageView iconview;
    private TextView dec;
    private TextView humidity;
    private TextView preasure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    private TextView temp;
    private TextView cloud;
    private Weather weather;
    private String miestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityname = (TextView) findViewById(R.id.vietove);
        iconview = (ImageView) findViewById(R.id.ikona);
        humidity = (TextView) findViewById(R.id.dregme);
        preasure = (TextView) findViewById(R.id.slegis);
        wind = (TextView) findViewById(R.id.vejas);
        sunrise = (TextView) findViewById(R.id.sauletekis);
        sunset = (TextView) findViewById(R.id.saulelidis);
        temp = (TextView) findViewById(R.id.temperatura);
        dec = (TextView) findViewById(R.id.aprasymas);
        updated = (TextView) findViewById(R.id.atnaujinimas);
        weather = new Weather();
        cloud = (TextView)findViewById(R.id.debesuotumas);
        Intent intent = getIntent();
        miestas = intent.getStringExtra("miestas");
        humidity.setVisibility(View.VISIBLE);
        temp.setVisibility(View.VISIBLE);
        dec.setVisibility(View.VISIBLE);
        sunrise.setVisibility(View.VISIBLE);
        sunset.setVisibility(View.VISIBLE);
        preasure.setVisibility(View.VISIBLE);
        wind.setVisibility(View.VISIBLE);
        cloud.setVisibility(View.VISIBLE);
        iconview.setVisibility(View.VISIBLE);
        updated.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Utils.BASE_URL+miestas+Utils.LOGIN_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

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
                    //weather.wind.setDeg(Utils.getfloat("deg", windobj));

                    JSONObject cloudobj = Utils.getObject("clouds", jsonObject);
                    weather.clouds.setPrecipitation(Utils.getint("all", cloudobj));

                    JSONObject mainobj = Utils.getObject("main",jsonObject);
                    weather.temperature.setMaxTemp(Utils.getfloat("temp_max",mainobj));
                    weather.temperature.setMinTemp(Utils.getfloat("temp_min",mainobj));
                    weather.currentCondition.setHumidity(Utils.getfloat("humidity",mainobj));
                    weather.currentCondition.setTemp(Utils.getint("temp",mainobj));
                    weather.currentCondition.setPreasure(Utils.getfloat("pressure",mainobj));

                    DateFormat df = DateFormat.getTimeInstance();
                    String sunrisedate = df.format(new Date(weather.location.getSunrise()));
                    String sunsetdate = df.format(new Date(weather.location.getSunset()));
                    String updatedate = df.format(new Date(weather.location.getLastUpdate()));

                    cityname.setText(weather.location.getCity());
                    humidity.setText("Dregme: "+weather.currentCondition.getHumidity()+"%");
                    temp.setText("Temperatura: "+weather.currentCondition.getTemp()+"C");
                    dec.setText("Aprasymas: "+weather.currentCondition.getDescription());
                    sunset.setText("Saule leidziasi: "+ sunsetdate);
                    sunrise.setText("Saule teka: "+ sunrisedate);
                    preasure.setText("Slegis: "+weather.currentCondition.getPreasure()+"hPa");
                    wind.setText("Vejo greitis: "+weather.wind.getSpeed()+"m/s");
                    cloud.setText("Debesuotumas "+weather.clouds.getPrecipitation()+"%");
                    updated.setText("Informacija atnaujinta: " + updatedate);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cityname.setText("Apie pasirinkta miesta "+miestas+" duomenu nera");
                humidity.setVisibility(View.INVISIBLE);
                temp.setVisibility(View.INVISIBLE);
                dec.setVisibility(View.INVISIBLE);
                sunrise.setVisibility(View.INVISIBLE);
                sunset.setVisibility(View.INVISIBLE);
                preasure.setVisibility(View.INVISIBLE);
                wind.setVisibility(View.INVISIBLE);
                cloud.setVisibility(View.INVISIBLE);
                iconview.setVisibility(View.INVISIBLE);
                updated.setVisibility(View.INVISIBLE);
                error.printStackTrace();
            }
        });
        AppControler.getmInstance().addToRequestQueue(jsonArrayRequest);
    }
}
