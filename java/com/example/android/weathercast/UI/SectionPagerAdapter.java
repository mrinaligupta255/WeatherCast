package com.example.android.weathercast.UI;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.weathercast.R;
import com.example.android.weathercast.WeatherData.CurrentWeather;
import com.example.android.weathercast.dummy.DummyContent;
import com.example.android.weathercast.dummy.DummyContent1;


import java.util.ArrayList;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter{
    SectionPagerAdapter.PlaceholderFragment c;
    static String s;


    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                return PlaceholderFragment.newInstance(0);

            case 1:
                return PlaceholderFragment1.newInstance(1);

            case 2:
                return PlaceholderFragment2.newInstance(2);
            case 3:
                return PlaceholderFragment3.newInstance(3);
        }
        return null;
        //return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MY LOCATIONS";
            case 1:
                return "CURRENT";
            case 2:
                return "HOURLY";
            case 3:
                return "WEEKLY";
        }
        return null;
    }

    public static class PlaceholderFragment extends Fragment{
        CurrentWeather mWeather;
        MainActivity mMainActivity = new MainActivity();
        private static final String ARG_SECTION_NUMBER = "section_number";
        public static boolean b = false;
        AppCompatActivity compatActivity = new AppCompatActivity();
        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;
        static int index = 0;
        private final String KEY_RECYCLER_STATE = "recycler_state";
        private static Bundle mBundleRecyclerViewState;
       static double lat,longi;
        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        View rootView;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            rootView = inflater.inflate(R.layout.fragment_main, container, false);
mWeather=new CurrentWeather();
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(compatActivity);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyRecyclerViewAdapter(getDataSet());
            mRecyclerView.setAdapter(mAdapter);

            return rootView;

        }


        public ArrayList<DataObject> getDataSet() {
            ArrayList results = new ArrayList<DataObject>();

            for (int i = 0; i < index; i++) {
                DataObject d = new DataObject(MyRecyclerViewAdapter.mDataset.get(i).getmText1(),
                        MyRecyclerViewAdapter.mDataset.get(i).getLat(),
                        MyRecyclerViewAdapter.mDataset.get(i).getLong(), false);
                results.add(d);
            }

            return results;
        }

        public void onResume() {
            super.onResume();
            ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                    .MyClickListener(){

                public void onItemClick(int position, View v) {
                    for (int i = 0; i < index; i++) {
                        MyRecyclerViewAdapter.mDataset.get(i).setMb(false);
                    }
                    MyRecyclerViewAdapter.mDataset.get(position).setMb(true);
b=true;
                    SectionPagerAdapter.s = MyRecyclerViewAdapter.mDataset.get(position).getmText1();
                    lat =  MyRecyclerViewAdapter.mDataset.get(position).mLat;
                     longi =  MyRecyclerViewAdapter.mDataset.get(position).mLong;
                    MainActivity.mTab.setText(s);

                  mMainActivity.getcal(lat,longi);

                }
            });
            if (mBundleRecyclerViewState != null) {
                Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
                mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
            }
        }

        public void onPause() {
            super.onPause();
            // save RecyclerView state
            mBundleRecyclerViewState = new Bundle();
            Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
            mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
        }

        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }
    }

    public static class PlaceholderFragment1 extends Fragment{
        private final String KEY_RECYCLER_STATE = "recycler_state";
        private static Bundle mBundleRecyclerViewState;
        private static final String ARG_SECTION_NUMBER = "section_number";
        MainActivity mMainActivity = new MainActivity();
        public static TextView t1,t2,t3,t4,t;


public  static  ImageView i;
        public PlaceholderFragment1() {

        }

        public static PlaceholderFragment1 newInstance(int sectionNumber) {
            PlaceholderFragment1 fragment = new PlaceholderFragment1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main1, container, false);
            setRetainInstance(true);

            t=(TextView)rootView.findViewById(R.id.time);
            t1 = (TextView) rootView.findViewById(R.id.summary);
            t2 = (TextView) rootView.findViewById(R.id.temperature);
            t3 = (TextView) rootView.findViewById(R.id.humidity);
            t4 = (TextView) rootView.findViewById(R.id.rain);
            i=(ImageView)rootView.findViewById(R.id.iconi);




            return rootView;
        }




        public void onResume() {
            super.onResume();

          
            }
        }

    public static class PlaceholderFragment2 extends Fragment{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private boolean mTwoPane;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_item_list, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.item_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView);


            if (rootView.findViewById(R.id.item_detail_container) != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true;
            }
            return rootView;
        }

        private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter((DummyContent.ITEMS), mTwoPane));
        }
    }

    public static class PlaceholderFragment3 extends Fragment{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private boolean mTwoPane;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_item_list, container, false);
            RecyclerView recyclerView1 = (RecyclerView) rootView.findViewById(R.id.item_list);
            assert recyclerView1 != null;
            setupRecyclerView((RecyclerView) recyclerView1);

            if (rootView.findViewById(R.id.item_detail_container1) != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true;
            }
            return rootView;
        }

        private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter1((DummyContent1.ITEMS), mTwoPane));
        }
    }

}

