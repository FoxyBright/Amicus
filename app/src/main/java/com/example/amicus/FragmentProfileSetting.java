package com.example.amicus;

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
import android.widget.Toast;

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
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new ProfileFragment());
                ft.commit();
            }
        });

        Button delete_bt = view.findViewById(R.id.delete_bt);

        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Сервис временно не доступен, пожалуйста попробуйте позже", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}