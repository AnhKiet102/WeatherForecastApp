package com.example.weatherforecastapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    Button btnSeach, btnChangeActivity;
    TextView txtcity,txtCountry, txtTemp,txtHumidity,txtwind,txtcloud, txtDay,txtStatus;
    ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
    }

    private void AnhXa() {
        editTextSearch=(EditText)findViewById(R.id.edittextSeachr);
        btnSeach=(Button)findViewById(R.id.btnSearch);
        btnChangeActivity=(Button)findViewById(R.id.btnchangeActivity);
        txtcity=(TextView)findViewById(R.id.txtCityname);
        txtCountry=(TextView)findViewById(R.id.txtnameCountry);
        txtcloud=(TextView)findViewById(R.id.txtCloud);
        txtHumidity=(TextView)findViewById(R.id.textHumidity);
        txtwind=(TextView)findViewById(R.id.txtWind);
        txtDay=(TextView)findViewById(R.id.textviewDay);
        txtTemp=(TextView)findViewById(R.id.textTemp);
        txtStatus=(TextView)findViewById(R.id.txtStatus);
        imgIcon=(ImageView)findViewById(R.id.imgIcon);

    }
}
