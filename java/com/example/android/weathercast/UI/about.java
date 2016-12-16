package com.example.android.weathercast.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.weathercast.R;

public class about extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView t;
        t=(TextView)findViewById(R.id.about);
        t.setText("Weather is the day to day condition of the atmosphere .This includes\n" +
                "temperature , rainfall and wind .Climate is the average weather conditions of a place ,\n" +
                "usually measured over one year.A nice and pleasant weather can make our day whereas bad\n" +
                "weather condition may spoil our day and scheduled plans. So it is very important to plan our\n" +
                "activities according to weather conditions and be aware of future weather situations .\n" +
                "WeatherCast, as the name suggest gives you pretty much all of the information you need about\n" +
                "weather, including extended forecasts, hourly forecasts, and the app itself is also well designed.Other\n" +
                "features includes Screenshot.");
    }
}
