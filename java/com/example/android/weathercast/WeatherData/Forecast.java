package com.example.android.weathercast.WeatherData;

/**
 * Created by Dell on 8/8/2016.
 */
public class Forecast{
    CurrentWeather mCurrentWeather;
    HourWeather[] mHourWeathers;
    DailyWeather[] mDailyWeathers;

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public HourWeather[] getHourWeathers() {
        return mHourWeathers;
    }

    public void setHourWeathers(HourWeather[] hourWeathers) {
        mHourWeathers = hourWeathers;
    }

    public DailyWeather[] getDailyWeathers() {
        return mDailyWeathers;
    }

    public void setDailyWeathers(DailyWeather[] dailyWeathers) {
        mDailyWeathers = dailyWeathers;
    }
}
