package com.example.amicus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class CardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        Button back_bt = view.findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout)getActivity().findViewById(R.id.replace_pay_set))
                        .setVisibility(View.INVISIBLE);
                ((LinearLayout)getActivity().findViewById(R.id.pay_set_layout))
                        .setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}