
package com.example.lenovoz51.oruprograma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    public class Forecast extends AppCompatActivity {

        private ListView prognoze;
        private TextView name;
        private Weather weather;
        private String miestas;
        private List<Weather> forecast;
        ContactAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forecast);
            Intent intent = getIntent();
            miestas = intent.getStringExtra("miestas");
            name = (TextView) findViewById(R.id.name);
            prognoze = (ListView) findViewById(R.id.prognoze);
            name.setVisibility(View.VISIBLE);
            prognoze.setVisibility(View.VISIBLE);
            forecast = new ArrayList<>();
            adapter = new ContactAdapter(this,forecast);
            prognoze.setAdapter(adapter);

            JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Utils.FORECAST_URL + miestas + Utils.LOGIN_URL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    JSONArray array = new JSONArray();
                    try {
                        array = response.getJSONArray("list");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < array.length(); i++) {

                        try {
                            Location location = new Location();
                            JSONObject jsonObject = array.getJSONObject(i);
                            Weather weather = new Weather();
                            JSONObject cityObj = Utils.getObject("city",response);
                            JSONObject coordObj = Utils.getObject("coord", cityObj);
                            location.setLan(Utils.getfloat("lat", coordObj));
                            location.setLon(Utils.getfloat("lon", coordObj));
                            location.setCountry(Utils.getString("country", cityObj));
                            location.setLastUpdate(Utils.getint("dt", jsonObject));
                            location.setCity(Utils.getString("name", cityObj));
                            weather.location = location;

                            name.setText(weather.location.getCity()+", "+weather.location.getCountry());
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

                            JSONObject mainobj = Utils.getObject("main", jsonObject);
                            weather.temperature.setMaxTemp(Utils.getfloat("temp_max", mainobj));
                            weather.temperature.setMinTemp(Utils.getfloat("temp_min", mainobj));
                            weather.currentCondition.setHumidity(Utils.getfloat("humidity", mainobj));
                            weather.currentCondition.setTemp(Utils.getint("temp", mainobj));
                            weather.currentCondition.setPreasure(Utils.getfloat("pressure", mainobj));

                            weather.time = Utils.getString("dt_txt", jsonObject);


                            forecast.add(weather);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                    prognoze.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent Tikslesne = new Intent(Forecast.this,TikslesnePrognoze.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("oras",forecast.get(i));
                            Tikslesne.putExtras(bundle);
                            startActivity(Tikslesne);
                        }
                    });
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    name.setText("Apie pasirinkta miesta " + miestas + " duomenu nera");
                    prognoze.setVisibility(View.INVISIBLE);
                }
            });


            AppControler.getmInstance().addToRequestQueue(jsonArrayRequest);
        }
    }
