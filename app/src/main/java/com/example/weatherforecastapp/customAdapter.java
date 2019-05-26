package com.example.weatherforecastapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter {
    Context context;

    public customAdapter(Context context, ArrayList<Thoitiet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    ArrayList<Thoitiet>arrayList;
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.dong_listview,null);
        Thoitiet thoitiet= arrayList.get(position);

        TextView textViewDay=(TextView)convertView.findViewById(R.id.textviewngaythang);
        TextView textViewStatus=(TextView)convertView.findViewById(R.id.textviewtrangthai);
        TextView textViewMin=(TextView)convertView.findViewById(R.id.textviewmin);
        TextView textViewMax=(TextView)convertView.findViewById(R.id.textviewmax);
        ImageView imageViewstatus=(ImageView)convertView.findViewById(R.id.imageviewtrangthai);
        Picasso.with(context).load("http://openweathermap.org/img/w/"+thoitiet.image+".png").into(imageViewstatus);

        textViewDay.setText(thoitiet.Day);
        textViewMax.setText(thoitiet.maxtemp+"°C");
        textViewMin.setText(thoitiet.minTemp+"°C");
        textViewStatus.setText(thoitiet.Status);




        return convertView;
    }
}
