package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.amicus.ui.SplashActivity;

public class ProfileFragment extends Fragment {

    Button profile_set_bt;
    Button auto_set_bt;
    Button pay_set_bt;
    Button app_set_bt;
    Button exit_bt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_set_bt = view.findViewById(R.id.profile_set_bt);
        auto_set_bt = view.findViewById(R.id.auto_set_bt);
        pay_set_bt = view.findViewById(R.id.pay_set_bt);
        app_set_bt = view.findViewById(R.id.app_set_bt);
        exit_bt = view.findViewById(R.id.exit_bt);

        exit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileFragment.this.getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        profile_set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new FragmentProfileSetting());
                ft4.commit();
            }
        });

        auto_set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new AutoFragment());
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
