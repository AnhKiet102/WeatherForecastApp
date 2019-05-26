package com.example.weatherforecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    String cityname;
    ImageView imageViewback;
    TextView textViewname;
    ListView lv;
   customAdapter customadapter;
   ArrayList<Thoitiet> mangthoitiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent   = getIntent()   ;
        Anhxa();
        String city= intent.getStringExtra("name");
        if(city.equals(""))
        {
            cityname="hanoi";
            Get7daydata(cityname);
        }
        else {
            cityname=city;
            Get7daydata(cityname);
        }
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void Anhxa() {
        imageViewback=(ImageView)findViewById(R.id.imageviewback);
        textViewname=(TextView)findViewById(R.id.textviewcityname);
        lv=(ListView)findViewById(R.id.listview);
        mangthoitiet = new ArrayList<Thoitiet>();
        customadapter = new customAdapter(Main3Activity.this,mangthoitiet);
        lv.setAdapter(customadapter);


    }

    private void Get7daydata(String data) {
        String url="http://api.openweathermap.org/data/2.5/forecast?q="+data+"&units=metric&cnt=7&appid=edaee9bfa304c001a6eb1da0cc4e90ce";
        RequestQueue requestQueue= Volley.newRequestQueue(Main3Activity.this);
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONObject jsonObjectCity= jsonObject.getJSONObject("city");
                            String name= jsonObjectCity.getString("name");
                            textViewname.setText(name);

                            JSONArray jsonArrayList= jsonObject.getJSONArray("list");
                            for(int i=0; i<jsonArrayList.length(); i++)
                            {
                                JSONObject jsonObjectList=jsonArrayList.getJSONObject(i);
                                String Ngay=jsonObjectList.getString("dt");


                                long l= Long.valueOf(Ngay);
                                Date date= new Date(l*1000L);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd ");
                                String Day= simpleDateFormat.format(date);

                                JSONObject jsonObjectTemp= jsonObjectList.getJSONObject("main");
                                String min= jsonObjectTemp.getString("temp_min");
                                String max= jsonObjectTemp.getString("temp_max");
                                Double  b= Double.valueOf(min);
                                Double  a= Double.valueOf(max);


                                String Nhietdomin= String.valueOf(b.intValue());
                                String Nhietdomax= String.valueOf(a.intValue());

                                JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                                JSONObject jsonObjectWeather= jsonArrayWeather.getJSONObject(0);

                                String status= jsonObjectWeather.getString("description");
                                String icon =jsonObjectWeather.getString("icon");
                                mangthoitiet.add(new Thoitiet(Day,status,icon,Nhietdomin,Nhietdomax));

                            }
                            customadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);

    }
}
