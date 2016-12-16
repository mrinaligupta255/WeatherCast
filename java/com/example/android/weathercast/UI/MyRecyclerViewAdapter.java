package com.example.android.weathercast.UI;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.weathercast.R;

import java.util.ArrayList;

/**
 * Created by Dell on 7/17/2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    public static ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        RecyclerView rv;
        TextView label;
        ImageButton b;

        public DataObjectHolder(View itemView) {
            super(itemView);

            label = (TextView) itemView.findViewById(R.id.textView);
            b=(ImageButton)itemView.findViewById(R.id.imageButton);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataSet) {
        mDataset=myDataSet;
    }
public MyRecyclerViewAdapter(){}
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        holder.label.setText(mDataset.get(position).getmText1());
        holder.b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });

    }

    public void addItem(DataObject dataObj,int index) {
        mDataset.add(index, dataObj);
        notifyItemChanged(index);
    }
    public void deleteItem(int index) {

        if(mDataset.get(index).isMb())
        {
            MainActivity.mTab.setText("My Locations");
        }
        mDataset.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index,mDataset.size());
        SectionPagerAdapter.PlaceholderFragment.index -=1;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
