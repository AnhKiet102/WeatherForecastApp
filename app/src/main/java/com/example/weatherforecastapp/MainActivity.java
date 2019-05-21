package com.example.weatherforecastapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        btnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= editTextSearch.getText().toString();
                GetcurrentWeatherData(city);
            }
        });
    }
    public  void GetcurrentWeatherData(String data)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url= "https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=edaee9bfa304c001a6eb1da0cc4e90ce";
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ketqua",response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
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
