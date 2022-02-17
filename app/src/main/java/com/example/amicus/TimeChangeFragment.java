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

public class TimeChangeFragment extends Fragment implements
        TimePicker.OnTimeChangedListener {

    private TimePicker timePicker;
    private Button btnSet;
    private TimePicker timePicker1;
    String timeFrom;
    String time_to;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_change, container, false);
        timePicker = view.findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(this);
        timePicker1 =view.findViewById(R.id.timePicker1);
        timePicker1.setOnTimeChangedListener(this);


        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == null) {
                    
                }
                timeFrom = timePicker.getHour() + ":" + timePicker.getMinute();
                time_to = timePicker1.getHour() + ":" + timePicker1.getMinute();
                Bundle bundle = new Bundle();
                bundle.putString("timeFrom", timeFrom);
                bundle.putString("timeTo", time_to);
                SearchFragment fragobj = new SearchFragment();
                fragobj.setArguments(bundle);
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, fragobj);
                ft4.commit();

            }
        });

            return view;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

    }
}