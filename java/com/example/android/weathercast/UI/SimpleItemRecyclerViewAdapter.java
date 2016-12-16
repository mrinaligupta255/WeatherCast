package com.example.android.weathercast.UI;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.weathercast.R;
import com.example.android.weathercast.dummy.DummyContent;

import java.util.List;

/**
 * Created by Dell on 7/14/2016.
 */
public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>{
    AppCompatActivity mAppCompatActivity = new AppCompatActivity();
    private final List<DummyContent.DummyItem> mValues;
    private boolean mTwoPane;

    public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items, boolean twoPlane) {
        mValues = items;
        mTwoPane = twoPlane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
      holder.mIdView.setText(mValues.get(position).time);
       holder.mContentView.setText(mValues.get(position).summary);
        holder.mTemp.setText(mValues.get(position).temp);
        holder.iconn.setImageResource(mValues.get(position).iconn);

        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mAppCompatActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public  TextView mIdView;
        public  TextView mContentView,mTemp;
        public DummyContent.DummyItem mItem;
        public ImageView iconn;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.list_time);
            mContentView = (TextView) view.findViewById(R.id.list_summary);
            mTemp=(TextView)view.findViewById(R.id.list_temperature);
            iconn=(ImageView)view.findViewById(R.id.list_icon);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
