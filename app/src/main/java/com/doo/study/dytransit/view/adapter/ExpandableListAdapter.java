package com.doo.study.dytransit.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.doo.study.dytransit.POJO.Stop;
import com.doo.study.dytransit.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dooyoungki on 1/3/16.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> headerList;
    private HashMap<String, List<Stop>> childListMap;

    public ExpandableListAdapter(Context context, List<String> headerList, HashMap<String, List<Stop>> childListMap) {
        this.childListMap = childListMap;
        this.context = context;
        this.headerList = headerList;
    }


    @Override
    public int getGroupCount() {
        return headerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childListMap.get(headerList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childListMap.get(headerList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerText = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.segment_list_header, null);
        }

        TextView headerView = (TextView) convertView.findViewById(R.id.expandable_list_header);
        headerView.setTypeface(null, Typeface.BOLD);
        headerView.setText(headerText);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Stop stop = (Stop) getChild(groupPosition, childPosition);
        String text = stop.getName();
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.segment_inner_item, null);
        }
        TextView childView = (TextView) convertView.findViewById(R.id.inner_list_item);
        if(text == null){
            text = "No name";
        }
        childView.setText(text);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
