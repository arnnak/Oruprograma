package com.example.lenovoz51.oruprograma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class TikslesnePrognoze extends AppCompatActivity {
    private TextView cityname;
    private ImageView iconview;
    private TextView dec;
    private TextView humidity;
    private TextView preasure;
    private TextView wind;
    private TextView updated;
    private TextView temp;
    private TextView cloud;
    private Weather weather;
    private String miestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tikslesne_prognoze);
        cityname = (TextView) findViewById(R.id.vietove);
        iconview = (ImageView) findViewById(R.id.ikona);
        humidity = (TextView) findViewById(R.id.dregme);
        preasure = (TextView) findViewById(R.id.slegis);
        wind = (TextView) findViewById(R.id.vejas);
        temp = (TextView) findViewById(R.id.temperatura);
        dec = (TextView) findViewById(R.id.aprasymas);
        updated = (TextView) findViewById(R.id.atnaujinimas);
        cloud = (TextView)findViewById(R.id.debesuotumas);
        Intent intent = getIntent();
        weather = (Weather) intent.getSerializableExtra("oras");
        DateFormat df = DateFormat.getTimeInstance();

        String updatedate = df.format(new Date(weather.location.getLastUpdate()));
        cityname.setText(weather.location.getCity()+", "+weather.location.getCountry());
        humidity.setText("Dregme: "+weather.currentCondition.getHumidity()+"%");
        temp.setText("Temperatura: "+weather.currentCondition.getTemp()+"C");
        dec.setText("Aprasymas: "+weather.currentCondition.getDescription());
        preasure.setText("Slegis: "+weather.currentCondition.getPreasure()+"hPa");
        wind.setText("Vejo greitis: "+weather.wind.getSpeed()+"m/s");
        cloud.setText("Debesuotumas "+weather.clouds.getPrecipitation()+"%");
        //updated.setText("Informacija atnaujinta: " + updatedate);
        updated.setText("Informacija atnaujinta: " + weather.time);
    }
}
