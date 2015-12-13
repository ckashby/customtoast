package com.meteoru.kalei.customtoast;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ArticlesAdapter extends ArrayAdapter<Article> {
    public ArticlesAdapter(Context context, List<Article> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }
}
