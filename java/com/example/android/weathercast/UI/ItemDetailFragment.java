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

/**
 * A fragment representing a single Item detail screen.

 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.time+"  "+mItem.summary);
                appBarLayout.setBackgroundResource(mItem.iconn1);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {

            ((TextView) rootView.findViewById(R.id.textView8)).setText(mItem.details[1]);
            ((TextView) rootView.findViewById(R.id.textView9)).setText(mItem.details[2]);
            ((TextView) rootView.findViewById(R.id.textView10)).setText(mItem.details[3]);
            ((TextView) rootView.findViewById(R.id.textView11)).setText(mItem.details[4]);
            ((TextView) rootView.findViewById(R.id.textView12)).setText(mItem.details[5]);
            ((TextView) rootView.findViewById(R.id.textView13)).setText(mItem.details[6]);

        }

        return rootView;
    }
}
