package com.example.amicus;

import static android.content.Context.MODE_PRIVATE;
import static com.example.amicus.MainActivity.facebook;
import static com.example.amicus.MainActivity.logo;
import static com.example.amicus.MainActivity.name1;
import static com.example.amicus.MainActivity.pochta;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.ui.SplashActivity;

public class ProfileFragment extends Fragment {

    Button profile_set_bt;
    Button auto_set_bt;
    Button pay_set_bt;
    Button app_set_bt;
    Button exit_bt;
    TextView name_prof;
    TextView pochta1;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_set_bt = view.findViewById(R.id.profile_set_bt);
        auto_set_bt = view.findViewById(R.id.auto_set_bt);
        pay_set_bt = view.findViewById(R.id.pay_set_bt);
        app_set_bt = view.findViewById(R.id.app_set_bt);
        exit_bt = view.findViewById(R.id.exit_bt);
        name_prof = view.findViewById(R.id.name_prof);
        pochta1 = view.findViewById(R.id.pochta);

        imageView = view.findViewById(R.id.profile_image);


        Glide.with(ProfileFragment.this).load(logo).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);

        name_prof.setText(name1);
        if (pochta1.equals("")) {
            pochta1.setText("Добавить E-mail");

        }
        pochta1.setText(pochta);



        exit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = getActivity().getSharedPreferences("remember", MODE_PRIVATE).edit();
                editor.putBoolean("remember", false);
                editor.commit();
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                startActivity(intent);
            }
        });

        profile_set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fm4 = getFragmentManager();
//                FragmentTransaction ft4= fm4.beginTransaction();
//                ft4.replace(R.id.fragment_container, new FragmentProfileSetting());
//                ft4.commit();

                Intent intent = new Intent(getActivity(),ProfileSetting.class);
                startActivity(intent);
            }
        });

        auto_set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new RecyclerAutoFragment());
                ft4.commit();
            }
        });

        pay_set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new FragmentPaySetting());
                ft4.commit();
            }
        });

        app_set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new FragmentAppSettings());
                ft4.commit();
            }
        });
        return view;
    }
}
