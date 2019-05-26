package com.example.weatherforecastapp;

public class Thoitiet {
    public String Day;
    public String Status;
    public String image;
    public String minTemp;
    public String maxtemp;
    public Thoitiet(String day, String status, String image, String minTemp, String maxtemp) {
        Day = day;
        Status = status;
        this.image = image;
        this.minTemp = minTemp;
        this.maxtemp = maxtemp;
    }
}
