package com.example.amicus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentProfileSetting extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_setting, container, false);

        TextView number_textview = view.findViewById(R.id.number_textview);
        number_textview.setText("9534637916");

        TextView mail_edit = view.findViewById(R.id.mail_edit);
        mail_edit.setText("grig@gmail.com");

        TextView facebook_edit = view.findViewById(R.id.facebook_edit);
        facebook_edit.setText("https://www.facebook.com/grig");

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

        return view;
    }
}