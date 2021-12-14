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
import android.widget.TextView;

import java.text.DecimalFormat;

public class CountPassagersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_passagers, container, false);

        ImageButton plus = view.findViewById(R.id.plus);
        ImageButton minus = view.findViewById(R.id.minus);
        TextView count = view.findViewById(R.id.count);
        DecimalFormat format = new DecimalFormat("0.#");
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = count.getText().toString();
                Double a = Double.parseDouble(s);
                count.setText(format.format(a + 1));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = count.getText().toString();
                Double a = Double.parseDouble(s);
                if (a > 1){
                    count.setText(format.format(a - 1));
                }
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