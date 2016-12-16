package com.example.android.weathercast.dummy;

import android.widget.TextView;

import com.example.android.weathercast.UI.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent1{

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 18;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "- -"," ","- -", makeDetails(),0,0);
    }

    private static String[] makeDetails() {
        String[] ii=new String[9];
        for(int i=0;i<9;i++)
            ii[i]="- -";
        return ii;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem{
        public  String id;
        public String time;
        public  String summary;
        public String[] details;
        public  String temp;
        public int iconn,iconn1;

        public DummyItem(String id, String summary,String temp,String time, String[] details,int icon,int icon1) {
            this.id = id;
            this.summary = summary;
            this.details = details;
            this.time=time;
            this.temp=temp;
            this.iconn=icon;
            this.iconn1=icon1;
        }

        @Override
        public String toString() {
            return summary;
        }
    }
}
