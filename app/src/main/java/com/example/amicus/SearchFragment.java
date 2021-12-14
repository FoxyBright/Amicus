package com.example.amicus;

import android.accounts.AccountManager;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SearchFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        LinearLayout calendar = view.findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new DayChangeFragment());
                ft4.commit();
            }
        });

        LinearLayout time = view.findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LinearLayout people = view.findViewById(R.id.people);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new CountPassagersFragment());
                ft4.commit();
            }
        });

        return view;
    }

}
