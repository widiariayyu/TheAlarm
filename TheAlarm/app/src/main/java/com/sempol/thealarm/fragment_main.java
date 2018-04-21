package com.sempol.thealarm;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sempol.thealarm.model.TimeModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//import org.jetbrains.annotations.Nullable;

/**
 * Created by Firelord on 08/04/2018.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class fragment_main extends Fragment {
    RecyclerView rvTime;
    List<TimeModel> times=new ArrayList<>();
    TimeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        //rvTime=view.findViewById(R.id.rv_time);
        setData();

        rvTime = (RecyclerView) getView().findViewById(R.id.rv_time);
        times.add(new TimeModel("06:00"));
        times.add(new TimeModel("07:00"));
        times.add(new TimeModel("08:00"));
        times.add(new TimeModel("09:00"));
        times.add(new TimeModel("10:00"));
        //adapter=new TimeAdapter(getActivity(),times);
        ArrayList<String> times2 = new ArrayList<>();
        times2.add("20:00");
        times2.add("10:00");
        times2.add("09:00");
        Log.i("TAG", "onViewCreated: " + times2.get(0));
        TimeAdapter timeAdapter = new TimeAdapter(getActivity(), times);
        rvTime.setAdapter(timeAdapter);
        rvTime.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return view;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setData(){
        times.add(new TimeModel("06:00"));
        times.add(new TimeModel("07:00"));
        times.add(new TimeModel("08:00"));
        times.add(new TimeModel("09:00"));
        times.add(new TimeModel("10:00"));

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("TAG", "onViewCreated: BBBB");
    }

}
