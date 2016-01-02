package com.doo.study.dytransit.view;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by dooyoungki on 12/31/15.
 */
public class MySearchView extends SearchView{
    private SearchView.SearchAutoComplete searchAutoComplete;

    public MySearchView(Context context) {
        super(context);
        searchAutoComplete = (SearchAutoComplete) findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setThreshold(1);
    }

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        searchAutoComplete.setOnItemClickListener(listener);
    }

    public void setAdapter(ArrayAdapter<?> adapter) {
        searchAutoComplete.setAdapter(adapter);
    }

    public void setText(String text) {
        searchAutoComplete.setText(text);
    }
}
