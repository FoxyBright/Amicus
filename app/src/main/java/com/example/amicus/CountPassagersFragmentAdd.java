package com.example.amicus;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;


public class CountPassagersFragmentAdd extends android.app.Fragment {


    TextView count1;
    String s;
    static int number1 = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_passagers_add, container, false);

        RelativeLayout plus = view.findViewById(R.id.plus);
        RelativeLayout minus = view.findViewById(R.id.minus);
        count1 = view.findViewById(R.id.count);
        DecimalFormat format = new DecimalFormat("0.#");
        count1.setText(String.valueOf(number1));
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1++;
                count1.setText(format.format(number1));

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1--;
                count1.setText(format.format(number1));
                if (number1 > 1){
                    count1.setText(format.format(number1));
                }
            }
        });

        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1 = Integer.parseInt(count1.getText().toString());
                AddFragment fragobj = new AddFragment();
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, fragobj);
                ft4.commit();
            }
        });

        Button back_bt = view.findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new AddFragment());
                ft4.commit();
            }
        });

        return view;
    }
}