package com.example.amicus;


import static com.example.amicus.MainActivity.facebook;
import static com.example.amicus.MainActivity.name1;
import static com.example.amicus.MainActivity.phone;
import static com.example.amicus.MainActivity.parol1;
import static com.example.amicus.MainActivity.pochta;
import static com.example.amicus.MainActivity.truba;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;


import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentProfileSetting extends Fragment {

    CircleImageView profile_image;
    private static final int PIC_IMAGE =1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_setting, container, false);

        TextView number_textview = view.findViewById(R.id.number_textview);
        TextView profile_name = view.findViewById(R.id.profile_name);
        profile_image = view.findViewById(R.id.profile_image);

        number_textview.setText(truba);
        profile_name.setText(name1);
        TextView facebook_edit = view.findViewById(R.id.facebook_edit);
        TextView mail_edit = view.findViewById(R.id.mail_edit);

        number_textview.setText(truba);
        profile_name.setText(name1);
        mail_edit.setText(pochta);
        facebook_edit.setText(facebook);
        if (pochta.equals("")) {
            mail_edit.setText("Добавить почту");
        }
        if(facebook.equals("")){
            facebook_edit.setText("Добавить Facebook");
        }


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