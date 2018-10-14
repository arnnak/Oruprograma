package com.example.lenovoz51.oruprograma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText city;
    private Button tvirtinti;
    private String miestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vietove);

        city = (EditText)findViewById(R.id.city);
        tvirtinti = (Button)findViewById(R.id.patvirtinti);

        tvirtinti.setOnClickListener(tvirtinticlick);
    }
    View.OnClickListener tvirtinticlick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            miestas = city.getText().toString();
            if(miestas.toCharArray().length !=0){
                Intent intent = new Intent(MainActivity.this,JsonWeatherParser.class);
                intent.putExtra("miestas",miestas);
                MainActivity.this.startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this,"Iveskite miesta", Toast.LENGTH_LONG).show();
            }
        }
    };
}
