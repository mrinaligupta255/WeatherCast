package com.example.android.weathercast.UI;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.weathercast.R;
import com.example.android.weathercast.dummy.DummyContent;
import com.example.android.weathercast.dummy.DummyContent1;

/**
 * A fragment representing a single Item detail screen.

 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment1 extends Fragment{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent1.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent1.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout1);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.summary);
                appBarLayout.setBackgroundResource(mItem.iconn1);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail1, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textView10)).setText(mItem.details[0]);
            ((TextView) rootView.findViewById(R.id.textView11)).setText(mItem.details[1]);
            ((TextView) rootView.findViewById(R.id.textView12)).setText(mItem.details[2]);
            ((TextView) rootView.findViewById(R.id.textView13)).setText(mItem.details[3]);
            ((TextView) rootView.findViewById(R.id.textView14)).setText(mItem.details[4]);
            ((TextView) rootView.findViewById(R.id.textView15)).setText(mItem.details[5]);
            ((TextView) rootView.findViewById(R.id.textView16)).setText(mItem.details[6]);
            ((TextView) rootView.findViewById(R.id.textView17)).setText(mItem.details[7]);
            ((TextView) rootView.findViewById(R.id.textView18)).setText(mItem.details[8]);


        }

        return rootView;
    }
}
