package com.example.weatherforecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    Button btnSeach, btnChangeActivity;
    TextView txtcity,txtCountry, txtTemp,txtHumidity,txtwind,txtcloud, txtDay,txtStatus;
    ImageView imgIcon;
    String City;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
       // GetcurrentWeatherData("Ha Noi");
        btnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= editTextSearch.getText().toString();
                if( city.equals(""))
                {
                    City="Ha Noi";
                    GetcurrentWeatherData(City);
                }
                else
                {
                    City=city;
                    GetcurrentWeatherData(City);
                }

            }
        });
        btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= editTextSearch.getText().toString();
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("name",city);
                startActivity(intent);
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
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day=jsonObject.getString("dt");
                            String name=jsonObject.getString("name");
                            txtcity.setText("Tên Thành Phố: "+name);

                            long l= Long.valueOf(day);
                            Date date= new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                            String Day= simpleDateFormat.format(date);
                            txtDay.setText(Day);
                            JSONArray jsonArrayWeather= jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather=jsonArrayWeather.getJSONObject(0);
                            String status= jsonObjectWeather.getString("main");
                            String icon=jsonObjectWeather.getString("icon");
                            Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/"+icon+".png").into(imgIcon);
                            txtStatus.setText(status);

                            JSONObject jsonObjectMain= jsonObject.getJSONObject("main");
                            String nhietdo= jsonObjectMain.getString("temp");
                            String doam= jsonObjectMain.getString("humidity");

                            Double  a= Double.valueOf(nhietdo);
                            String NhietDO= String.valueOf(a.intValue());
                             txtTemp.setText(NhietDO+"°C");
                             txtHumidity.setText(doam);

                             JSONObject jsonObjectWind= jsonObject.getJSONObject("wind");
                             String gio= jsonObjectWind.getString("speed");
                             txtwind.setText(gio+"m/s");

                             JSONObject jsonObjectCloud= jsonObject.getJSONObject("clouds");
                             String may= jsonObjectCloud.getString("all");
                             txtcloud.setText(may+"%");

                             JSONObject jsonObjectsys= jsonObject.getJSONObject("sys");
                             String country= jsonObjectsys.getString("country");
                             txtCountry.setText("Tên quốc gia: "+ country);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
