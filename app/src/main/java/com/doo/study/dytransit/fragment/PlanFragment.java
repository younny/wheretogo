package com.doo.study.dytransit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doo.study.dytransit.POJO.RouteSet;
import com.doo.study.dytransit.POJO.Stop;
import com.doo.study.dytransit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dooyoungki on 12/31/15.
 */
public class PlanFragment extends Fragment {
    public static final String TAG = PlanFragment.class.getSimpleName();

    private RouteSet routeSet;
    private List<Stop> stops = new ArrayList<>();
    public static PlanFragment newInstance() {
        return new PlanFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }




}
