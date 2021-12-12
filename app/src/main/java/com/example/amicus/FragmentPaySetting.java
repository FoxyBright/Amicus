package com.example.amicus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.amicus.databinding.FragmentCardViewBinding;

public class FragmentPaySetting extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_setting, container, false);

        Button back_bt = view.findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout)getActivity().findViewById(R.id.fragment_replace_layout))
                        .setVisibility(View.INVISIBLE);
                ((LinearLayout)getActivity().findViewById(R.id.profile_layout))
                        .setVisibility(View.VISIBLE);
            }
        });

        LinearLayout replace_pay_set = view.findViewById(R.id.replace_pay_set);
        LinearLayout pay_set_layout = view.findViewById(R.id.pay_set_layout);
        LinearLayout card = view.findViewById(R.id.card);
        LinearLayout add_card = view.findViewById(R.id.add_card);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_replace, new CardFragment());
                ft.commit();
                replace_pay_set.setVisibility(View.VISIBLE);
                pay_set_layout.setVisibility(View.INVISIBLE);
            }
        });

        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_replace, new CardViewFragment());
                ft.commit();
                replace_pay_set.setVisibility(View.VISIBLE);
                pay_set_layout.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }
}