package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeChangeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_change, container, false);

        TimePicker arrival_hours = view.findViewById(R.id.arrival_hours);
        NumberPicker arrival_minutes = view.findViewById(R.id.arrival_minutes);
        TimePicker departure_hours = view.findViewById(R.id.departure_hours);
        NumberPicker departure_minutes = view.findViewById(R.id.departure_minutes);

        arrival_hours.setIs24HourView(true);
        departure_hours.setIs24HourView(true);
        departure_minutes.setMaxValue(60);
        arrival_minutes.setMaxValue(60);

        Button back_bt = view.findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new SearchFragment());
                ft.commit();
            }
        });

        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new SearchFragment());
                ft.commit();
            }
        });

            return view;
    }
}