package com.example.android.weathercast.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.weathercast.R;
import com.example.android.weathercast.WeatherData.Forecast;
import com.example.android.weathercast.WeatherData.HourWeather;
import com.example.android.weathercast.WeatherData.CurrentWeather;
import com.example.android.weathercast.WeatherData.DailyWeather;
import com.example.android.weathercast.dummy.DummyContent;
import com.example.android.weathercast.dummy.DummyContent1;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{
    private SectionPagerAdapter mSectionsPagerAdapter;
    public CustomViewPager mViewPager,mm;
    TabLayout tabLayout;
    int hourLength;
    LinearLayout tabStrip,cc;
    HourWeather[] mHourWeather;
    DailyWeather[] mDailyWeather;
    public CurrentWeather mCurrentWeather;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    String mplace;
    public static TabLayout.Tab mTab,q;
    OkHttpClient client;
    String ApiKey;
    public static View view;
    SectionPagerAdapter.PlaceholderFragment c;

    String jsonData;
    private GoogleApiClient mClient;
    FloatingActionButton refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = new SectionPagerAdapter.PlaceholderFragment();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        mSectionsPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(3);


        mViewPager.setAdapter(mSectionsPagerAdapter);
         tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);
        mTab = tabLayout.getTabAt(0);
      /*  tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        tabStrip.setEnabled(false);
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setClickable(false);

        }*/
if(isNetworkAvailable())
{}
        else
    Toast.makeText(MainActivity.this, "Network Unavailable. Please Try again Later!", Toast.LENGTH_LONG).show();
        refresh = (FloatingActionButton) findViewById(R.id.refreshk);
        refresh.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                if (SectionPagerAdapter.PlaceholderFragment.b == true)
                {
                    getcal(SectionPagerAdapter.PlaceholderFragment.lat, SectionPagerAdapter.PlaceholderFragment.longi);
                    Toast.makeText(MainActivity.this, "Data is refreshed!", Toast.LENGTH_SHORT).show();   }
                else
                    Toast.makeText(MainActivity.this, "Please select a city to proceed!", Toast.LENGTH_SHORT).show();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    int z=0;
   public void  getcal(double Lat,double Longi) {
            client = new OkHttpClient();
            ApiKey = "511ba11803d8b5c322547d58bb66850d";
            String foreCast = "https://api.forecast.io/forecast/" + ApiKey + "/" +Lat + "," + Longi;
            Request request = new Request.Builder()
                    .url(foreCast).get()
                    .build();
            client.newCall(request).enqueue(new Callback(){
                @Override
                public void onFailure(Call call, IOException e) {
                    alertUserAboutError();}
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                         jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetails(jsonData);
                            mHourWeather=getHourDetails(jsonData);
                            mDailyWeather=getDailyDetails(jsonData);
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run() {
                                    updateCurrentWeather();
                                    updateHourWeather();
                                    updateDailyWeather();
                                    z=1;
                                }
                            });} else {
                            alertUserAboutError();
                        }} catch (IOException e) {}
                    catch (JSONException e) {}
                }});
     /* else {
            Toast.makeText(MainActivity.this, "Network Unavailable. Please Try again Later!", Toast.LENGTH_LONG).show();
        }*/
   }





    public void updateHourWeather()
{
    for(int i=0;i<mHourWeather.length;i++)
    {DummyContent.ITEMS.get(i).iconn1=mHourWeather[i].getIconId1();
        DummyContent.ITEMS.get(i).summary=""+mHourWeather[i].getSummary();
      DummyContent.ITEMS.get(i).temp=String.format("%.2f°F",mHourWeather[i].getTemperature());
      DummyContent.ITEMS.get(i).time=""+mHourWeather[i].getFormattedTime();
        DummyContent.ITEMS.get(i).iconn=mHourWeather[i].getIconId();
        DummyContent.ITEMS.get(i).details[0]= mHourWeather[i].getFormattedTime()+"";
        DummyContent.ITEMS.get(i).details[1]= String.format("%.2f°F",mHourWeather[i].getTemperature());
        DummyContent.ITEMS.get(i).details[2]= String.format("%.2f",mHourWeather[i].getPrecipChance())+"%";
        DummyContent.ITEMS.get(i).details[3]= String.format("%.2f",mHourWeather[i].getHumidity())+"%";
        DummyContent.ITEMS.get(i).details[4]=""+ mHourWeather[i].getWindSpeed();
        DummyContent.ITEMS.get(i).details[5]=""+ mHourWeather[i].getPressure();
        DummyContent.ITEMS.get(i).details[6]=""+ mHourWeather[i].getOzone();

    }
}
    public void updateDailyWeather()
    {int i;
        for(i=0;i<mDailyWeather.length;i++)
        {
            DummyContent1.ITEMS.get(i).summary=""+mDailyWeather[i].getSummary();
            DummyContent1.ITEMS.get(i).time=""+mDailyWeather[i].getDayOfTheWeek();
DummyContent1.ITEMS.get(i).iconn1=mDailyWeather[i].getIconId1();
            DummyContent1.ITEMS.get(i).iconn=mDailyWeather[i].getIconId();
            DummyContent1.ITEMS.get(i).details[0]=""+ mDailyWeather[i].getSunriseFormattedTime();
            DummyContent1.ITEMS.get(i).details[1]=""+ mDailyWeather[i].getSunsetFormattedTime();
            DummyContent1.ITEMS.get(i).details[2]= String.format("%.2f°F",mDailyWeather[i].getTemperatureMax());
            DummyContent1.ITEMS.get(i).details[3]= String.format("%.2f°F",mDailyWeather[i].getTemperatureMin());
            DummyContent1.ITEMS.get(i).details[4]= String.format("%.2f",mDailyWeather[i].getPrecipChance())+"%";
            DummyContent1.ITEMS.get(i).details[5]=String.format("%.2f", mDailyWeather[i].getHumidity())+"%";
            DummyContent1.ITEMS.get(i).details[6]=""+ mDailyWeather[i].getWindSpeed();
            DummyContent1.ITEMS.get(i).details[7]=""+ mDailyWeather[i].getOzone();
            DummyContent1.ITEMS.get(i).details[8]=""+ mDailyWeather[i].getPressure();


        }
        for (int j=i;j<18;j++)
        {
            DummyContent1.ITEMS.get(j).summary=" ";
            DummyContent1.ITEMS.get(j).time=" ";
            DummyContent.ITEMS.get(j).temp=" ";
        }
    }
    public void updateCurrentWeather() {
        SectionPagerAdapter.PlaceholderFragment1.t.setText(mCurrentWeather.getFormattedTime());
        SectionPagerAdapter.PlaceholderFragment1.t1.setText(mCurrentWeather.getSummary());
        SectionPagerAdapter.PlaceholderFragment1.t2.setText(String.format("%.2f°F",mCurrentWeather.getTemperature()));
        SectionPagerAdapter.PlaceholderFragment1.t3.setText(String.format("%.2f",mCurrentWeather.getHumidity())+"%");
        SectionPagerAdapter.PlaceholderFragment1.t4.setText(mCurrentWeather.getPrecipChance()+"%");
        SectionPagerAdapter.PlaceholderFragment1.i.setImageResource(mCurrentWeather.getIconId());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void findPlace(View view) {
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                    .build();
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(typeFilter)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);


        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                mplace = place.getName() + "";
                LatLng mlatlng = place.getLatLng();

                boolean a = false;
                for (int i = 0; i < SectionPagerAdapter.PlaceholderFragment.index; i++) {
                    if (mplace.equals(MyRecyclerViewAdapter.mDataset.get(i).getmText1())) {
                        a = true;
                    }
                }
                if (a) {
                    Toast.makeText(MainActivity.this, "City is alredy selected!", Toast.LENGTH_LONG).show();
                } else {
                    DataObject b = new DataObject(mplace, mlatlng.latitude, mlatlng.longitude, false);
                    MyRecyclerViewAdapter mAdapter1 = new MyRecyclerViewAdapter();
                    mAdapter1.addItem(b, SectionPagerAdapter.PlaceholderFragment.index);
                    SectionPagerAdapter.PlaceholderFragment.index += 1;
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }


    private DailyWeather[] getDailyDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        JSONObject Daily=forecast.getJSONObject("daily");

        JSONArray data=Daily.getJSONArray("data");
        DailyWeather[] dailyWeathers=new DailyWeather[data.length()];
        for(int i=0;i<data.length();i++)
        {
            JSONObject jsonDaily=data.getJSONObject(i);

            DailyWeather daily=new DailyWeather();

            daily.setHumidity(jsonDaily.getDouble("humidity"));
            daily.setTemperatureMax(jsonDaily.getDouble("temperatureMax"));
            daily.setTemperatureMin(jsonDaily.getDouble("temperatureMin"));
           daily.setTime(jsonDaily.getLong("time"));
            daily.setSunriseTime(jsonDaily.getLong("sunriseTime"));
            daily.setSunsetTime(jsonDaily.getLong("sunsetTime"));
            daily.setIcon(jsonDaily.getString("icon"));
            daily.setPrecipChance(jsonDaily.getDouble("precipProbability"));
            daily.setSummary(jsonDaily.getString("summary"));
            daily.setTimeZone(timezone);

            dailyWeathers[i]=daily;
        }
        return dailyWeathers;
    }

    private HourWeather[] getHourDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        JSONObject hourly=forecast.getJSONObject("hourly");
        JSONArray data=hourly.getJSONArray("data");
        JSONObject Daily=forecast.getJSONObject("daily");

        JSONArray data1=Daily.getJSONArray("data");
        HourWeather[] hourWeathers=new HourWeather[data.length()];
        hourLength=data1.length();
        for(int i=0;i<data.length();i++)
        {
            JSONObject jsonHour=data.getJSONObject(i);
            HourWeather hour=new HourWeather();
            hour.setPressure(jsonHour.getDouble("pressure"));
            hour.setOzone(jsonHour.getDouble("ozone"));
            hour.setWindSpeed(jsonHour.getDouble("windSpeed"));
            hour.setHumidity(jsonHour.getDouble("humidity"));
            hour.setTemperature(jsonHour.getDouble("temperature"));
            hour.setTime(jsonHour.getLong("time"));
            hour.setIcon(jsonHour.getString("icon"));
            hour.setPrecipChance(jsonHour.getDouble("precipProbability"));
            hour.setSummary(jsonHour.getString("summary"));
            hour.setTimeZone(timezone);

            hourWeathers[i]=hour;
        }
        return  hourWeathers;
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {


        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject currently = forecast.getJSONObject("currently");
        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);
        Log.d("kkk", currentWeather.getFormattedTime());
        return currentWeather;
    }

    public void alertUserAboutError() {
        AlertDialogBox dialog = new AlertDialogBox();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }


    public void popup(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_share, popupMenu.getMenu());
        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popupMenu);
            argTypes = new Class[]{boolean.class};
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {
            // Possible exceptions are NoSuchMethodError and NoSuchFieldError
            //
            // In either case, an exception indicates something is wrong with the reflection code, or the
            // structure of the PopupMenu class or its dependencies has changed.
            //
            // These exceptions should never happen since we're shipping the AppCompat library in our own apk,
            // but in the case that they do, we simply can't force icons to display, so log the error and
            // show the menu normally.

            // Log.w(TAG, "error forcing menu icons to show", e);
            popupMenu.show();
            return;
        }

        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, about.class);
            startActivity(intent);
        }
        if (id == R.id.action_add) {
            findPlace(findViewById(R.id.action_add));

        }

        return super.onOptionsItemSelected(item);
    }
public void createToast()
{
    Toast.makeText(MainActivity.this, "Scroll UP-DOWN for better data loading", Toast.LENGTH_LONG).show();
}

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.weathercast/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.weathercast/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        mClient.disconnect();
    }
}
class DataObject{
    private String mText1;
    double mLat, mLong;
    boolean mb;

    DataObject(String text1, double lat, double longi, boolean b) {
        mText1 = text1;
        mLong = longi;
        mLat = lat;
        mb = b;
    }

    public boolean isMb() {
        return mb;
    }

    public void setMb(boolean mb) {
        this.mb = mb;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLong() {
        return mLong;
    }

    public void setLong(double aLong) {
        mLong = aLong;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }


}