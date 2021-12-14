package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DayChangeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_change, container, false);

        Button Sun_bt = view.findViewById(R.id.Sun_bt);
        Button Mon_bt = view.findViewById(R.id.Mon_bt);
        Button Tus_bt = view.findViewById(R.id.Tus_bt);
        Button Wen_bt = view.findViewById(R.id.Wen_bt);
        Button Thu_bt = view.findViewById(R.id.Thu_bt);
        Button Fri_bt = view.findViewById(R.id.Fri_bt);
        Button Sut_bt = view.findViewById(R.id.Sut_bt);


        Sun_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sun_bt.getBackground().equals(R.drawable.days_active_back)){

                    Sun_bt.setBackgroundResource(R.drawable.days_passive_back);
                    Sun_bt.setTextColor(Color.parseColor("#3E4958"));

                }
                else {

                    Sun_bt.setBackgroundResource(R.drawable.days_active_back);
                    Sun_bt.setTextColor(Color.parseColor("#ffffff"));

                }
            }
        });

        Mon_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Tus_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Wen_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Thu_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Fri_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Sut_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button back_bt = view.findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new SearchFragment());
                ft4.commit();
            }
        });

        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new SearchFragment());
                ft4.commit();
            }
        });

        return view;
    }
}