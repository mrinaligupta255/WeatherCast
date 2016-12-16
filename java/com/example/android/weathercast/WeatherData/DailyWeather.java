package com.example.android.weathercast.WeatherData;
import android.util.Log;

import com.example.android.weathercast.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
public class DailyWeather{
    private String mIcon;private long mTime,mSunsetTime,mSunriseTime;
    private double mTemperatureMin,mTemperatureMax, mHumidity, mPrecipChance;private String mSummary;
    private String mTimeZone;private double mPressure, mOzone,mWindSpeed;
    public String getIcon() {
        return mIcon;
    } public int getIconId() {
        int iconId = R.drawable.clear_day;
        if (mIcon.equals("clear-day"))
            iconId = R.drawable.clear_day;
        else if (mIcon.equals("clear-night"))
            iconId = R.drawable.clear_night;
        else if (mIcon.equals("rain"))
            iconId = R.drawable.rain;
        else if (mIcon.equals("snow"))
            iconId = R.drawable.snow;
        else if (mIcon.equals("hail"))
            iconId = R.drawable.hail;
        else if (mIcon.equals("sleet"))
            iconId = R.drawable.sleet;
        else if (mIcon.equals("cloudy"))
            iconId = R.drawable.cloudy;
        else if (mIcon.equals("windy"))
            iconId = R.drawable.wind;
        else if (mIcon.equals("thunderstorm"))
            iconId = R.drawable.thunderstorm;
        else if (mIcon.equals("fog"))
            iconId = R.drawable.fog;
        else if (mIcon.equals("partly-cloudy-day"))
            iconId = R.drawable.partly_cloudy_day;
        else if (mIcon.equals("partly-cloudy-night"))
            iconId = R.drawable.overcast;
        else if (mIcon.equals("tornado"))
            iconId = R.drawable.tornato;

        return iconId;
    }
    public String getTimeZone() {
        return mTimeZone;
    } public String getSunriseFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getSunriseTime() * 1000);
        String timeString = formatter.format(dateTime);
        return timeString;
    }
    public String getSunsetFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getSunsetTime() * 1000);
        String timeString = formatter.format(dateTime);
        return timeString;
    } public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime() * 1000);
        String timeString = formatter.format(dateTime);
        return timeString;
    }
    public String getDayOfTheWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date dateTime = new Date(mTime * 1000);
        return formatter.format(dateTime);
    } public double getPressure() {
        return mPressure;
    }
    public void setPressure(double pressure) {
        mPressure = pressure;
    }
    public double getOzone() {
        return mOzone;
    } public void setOzone(double ozone) {
        mOzone = ozone;
    }
    public double getWindSpeed() {
        return mWindSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        mWindSpeed = windSpeed;
    }
    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }
    public void setIcon(String icon) {
        mIcon = icon;
    } public String getSummary() {
        return mSummary;
    }
    public void setSummary(String summary) {
        mSummary = summary;
    }
    public double getPrecipChance() {
        return mPrecipChance*100;
    }
    public void setPrecipChance(double precipChance) {
        mPrecipChance = precipChance;
    }
    public double getHumidity() {
        return mHumidity*100;
    }
    public void setHumidity(double humidity) {
        mHumidity = humidity;
    } public double getTemperatureMax() {

        return mTemperatureMax;
    }
    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
    }
    public int getIconId1() {
        final Random rnd = new Random();
        // clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy,
        // partly-cloudy-day, or partly-cloudy-night, hail, thunderstorm, or tornado,
        int iconId = R.drawable.clear_day;
        int i;
        i = rnd.nextInt(9) + 1;
        if (mIcon.equals("clear-day")) {
           int[] images = {R.drawable.cd1, R.drawable.cd2, R.drawable.cd3,
                    R.drawable.cd4, R.drawable.cd5, R.drawable.cd6, R.drawable.cd7,
                    R.drawable.cd8, R.drawable.cd9, R.drawable.cd10};

            iconId = images[i];

        } else if (mIcon.equals("clear-night")) {
            int[] images = {R.drawable.cn1, R.drawable.cn2, R.drawable.cn3,
                    R.drawable.cn4, R.drawable.cn5, R.drawable.cn6, R.drawable.cn7,
                    R.drawable.cn8, R.drawable.cn9, R.drawable.cn10}; iconId = images[i];

        } else if (mIcon.equals("rain")) {
              int[] images = {R.drawable.r1, R.drawable.r2, R.drawable.r3,
                    R.drawable.r4, R.drawable.r5, R.drawable.r6, R.drawable.r7,
                    R.drawable.r8, R.drawable.r9, R.drawable.r10};
            iconId = images[i];
        } else if (mIcon.equals("snow")) {
             int[] images = {R.drawable.s1, R.drawable.s2, R.drawable.s3,
                    R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7,
                    R.drawable.s8, R.drawable.s9, R.drawable.s10};
            iconId = images[i];
        } else if (mIcon.equals("hail")) {
             int[] images = {R.drawable.h1, R.drawable.h2, R.drawable.h3,
                    R.drawable.h4, R.drawable.h5, R.drawable.h6, R.drawable.h7,
                    R.drawable.h8, R.drawable.h9, R.drawable.h10};
            iconId = images[i];
        } else if (mIcon.equals("sleet")) {
             int[] images = {R.drawable.cn1, R.drawable.cn2, R.drawable.cn3,
                    R.drawable.cn4, R.drawable.cn5, R.drawable.cn6, R.drawable.cn7,
                    R.drawable.cn8, R.drawable.cn9, R.drawable.cn10};
            iconId = images[i];
        } else if (mIcon.equals("cloudy")) {
              int[] images = {R.drawable.c1, R.drawable.c2, R.drawable.c3,
                    R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7,
                    R.drawable.c8, R.drawable.c9, R.drawable.c10};
            iconId = images[i];
        } else if (mIcon.equals("windy")) {
             int[] images = {R.drawable.w1, R.drawable.w2, R.drawable.w3,
                    R.drawable.w4, R.drawable.w5, R.drawable.w6, R.drawable.w7,
                    R.drawable.w8, R.drawable.w9, R.drawable.w10};
            iconId = images[i];
        } else if (mIcon.equals("thunderstorm"))
            iconId = R.drawable.thunderstorm;
        else if (mIcon.equals("fog"))
            iconId = R.drawable.fog;
        else if (mIcon.equals("partly-cloudy-day")) {
            int[] images = {R.drawable.pcd1, R.drawable.pcd2, R.drawable.pcd3,
                    R.drawable.pcd4, R.drawable.pcd5, R.drawable.pcd6, R.drawable.pcd7,
                    R.drawable.pcd8, R.drawable.pcd9, R.drawable.pcd10};
            iconId = images[i];
        } else if (mIcon.equals("partly-cloudy-night")) {
             int[] images = {R.drawable.pcn1, R.drawable.pcn2, R.drawable.pcn3,
                    R.drawable.pcn4, R.drawable.pcn5, R.drawable.pcn6, R.drawable.pcn7,
                    R.drawable.pcn8, R.drawable.pcn9, R.drawable.pcn10};
            iconId = images[i];
        } else if (mIcon.equals("tornado"))
            iconId = R.drawable.tornato;

        return iconId;
    } public double getTemperatureMin() {
        return mTemperatureMin;
    }
    public void setTemperatureMin(double temperatureMin) {
        mTemperatureMin = temperatureMin;
    }
    public long getSunriseTime() {
        return mSunriseTime;
    }
    public void setSunriseTime(long sunriseTime) {
        mSunriseTime = sunriseTime;
    }
    public long getSunsetTime() {
        return mSunsetTime;
    }
    public void setSunsetTime(long sunsetTime) {
        mSunsetTime = sunsetTime;
    }
    public long getTime() {
        return mTime;
    } public void setTime(long time) {
        mTime = time;
    }
}
