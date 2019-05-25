package com.example.weatherforecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    String cityname;
    ImageView imageViewback,imageViewtrangthai;
    TextView textViewngaythang,textViewtrangthai,textViewmin,textViewmax,textViewname;
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

    }

    private void Anhxa() {
        imageViewback=(ImageView)findViewById(R.id.imageviewback);
        textViewname=(TextView)findViewById(R.id.textviewcityname);
        lv=(ListView)findViewById(R.id.listview);
        mangthoitiet = new ArrayList<Thoitiet>();
        customadapter = new customAdapter(Main3Activity.this,mangthoitiet);


    }

    private void Get7daydata(String data) {
        String url="http://api.openweathermap.org/data/2.5/forecast?q="+data+"&units=metric&cnt=7&appid=edaee9bfa304c001a6eb1da0cc4e90ce";
        RequestQueue requestQueue= Volley.newRequestQueue(Main3Activity.this);
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
