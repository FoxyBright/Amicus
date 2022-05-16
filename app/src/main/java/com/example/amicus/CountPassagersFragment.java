package com.example.amicus;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class CountPassagersFragment extends Fragment {


    TextView count;
    String s;
    static int number = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_passagers, container, false);

        RelativeLayout plus = view.findViewById(R.id.plus);
        RelativeLayout minus = view.findViewById(R.id.minus);
        count = view.findViewById(R.id.count);
        DecimalFormat format = new DecimalFormat("0.#");
        count.setText(String.valueOf(number));
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               number++;
                count.setText(format.format(number));

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number--;
                count.setText(format.format(number));
                if (number > 1){
                    count.setText(format.format(number));
                }
            }
        });

        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = Integer.parseInt(count.getText().toString());
                SearchFragment fragobj = new SearchFragment();
                android.app.FragmentManager fm4 = getFragmentManager();
                android.app.FragmentTransaction ft4= fm4.beginTransaction();
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
                ft4.replace(R.id.fragment_container, new SearchFragment());
                ft4.commit();
            }
        });

        return view;
    }
}