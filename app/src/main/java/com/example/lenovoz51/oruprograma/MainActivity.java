package com.example.lenovoz51.oruprograma;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityname = (TextView)findViewById(R.id.vietove);
        iconview = (ImageView) findViewById(R.id.ikona);
        humidity = (TextView)findViewById(R.id.dregme);
        preasure = (TextView)findViewById(R.id.slegis);
        wind = (TextView)findViewById(R.id.vejas);
        sunrise = (TextView)findViewById(R.id.sauletekis);
        sunset = (TextView)findViewById(R.id.saulelidis);
        temp = (TextView)findViewById(R.id.temperatura);
        dec = (TextView)findViewById(R.id.aprasymas);
        updated = (TextView)findViewById(R.id.atnaujinimas);

        gavimas();


    }

    private void gavimas(){
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(Utils.BASE_URL);
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather>{

        @Override
        protected Weather doInBackground(String... strings) {
            String data = (new WeatherHttpClient()).getWeatherData(Utils.BASE_URL);
            Weather weather = JsonWeatherParser.getWeather(data);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather){
            super.onPostExecute(weather);
            /*cityname.setText(weather.location.getCity());
            humidity.setText((int) weather.currentCondition.getHumidity());
            temp.setText((int) weather.temperature.getTemp());
            dec.setText(weather.currentCondition.getDescription());
            sunset.setText((int) weather.location.getSunset());
            sunrise.setText((int) weather.location.getSunrise());*/
        }
    }
}
