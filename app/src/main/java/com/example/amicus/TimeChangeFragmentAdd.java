package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

public class TimeChangeFragmentAdd extends Fragment implements
        TimePicker.OnTimeChangedListener {

    private TimePicker timePicker;
    private TimePicker timePicker1;
    static String timeFrom2;
    static String time_to2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_change_add, container, false);
        timePicker = view.findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(this);
        timePicker1 =view.findViewById(R.id.timePicker1);
        timePicker1.setOnTimeChangedListener(this);
        timePicker.setIs24HourView(true);
        timePicker1.setIs24HourView(true);


        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeFrom2 = timePicker.getHour() + ":" + timePicker.getMinute();
                time_to2 = timePicker1.getHour() + ":" + timePicker1.getMinute();
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new AddFragment());
                ft4.commit();
            }
        });

        return view;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

    }
}