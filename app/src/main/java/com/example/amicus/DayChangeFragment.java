package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;


public class DayChangeFragment extends Fragment {

    CheckBox mColorCheckBox,mColorCheckBox2,mColorCheckBox3,mColorCheckBox4,
            mColorCheckBox5,mColorCheckBox6,mColorCheckBox7;
    String pn = "пн ";
    String vt = "вт ";
    String sr = "ср ";
    String cht = "чт ";
    String pt = "пт ";
    String sn = "сб ";
    String st = "вс";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_change, container, false);

        mColorCheckBox = view.findViewById(R.id.checkBoxColor);
        mColorCheckBox2 = view.findViewById(R.id.checkBoxColor2);
        mColorCheckBox3 = view.findViewById(R.id.checkBoxColor3);
        mColorCheckBox4 = view.findViewById(R.id.checkBoxColor4);
        mColorCheckBox5 = view.findViewById(R.id.checkBoxColor5);
        mColorCheckBox6 = view.findViewById(R.id.checkBoxColor6);
        mColorCheckBox7 = view.findViewById(R.id.checkBoxColor7);

        mColorCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mColorCheckBox.isChecked()) {
                    pn = mColorCheckBox.getText().toString() + " ";
                }else{
                    pn = ".";
                }
            }
        });
        mColorCheckBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mColorCheckBox2.isChecked()) {
                    vt = mColorCheckBox2.getText().toString() + " ";
                }else{
                    vt = ".";
                }
            }
        });
        mColorCheckBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mColorCheckBox3.isChecked()) {
                    sr = mColorCheckBox3.getText().toString() + " ";
                }else{
                    sr = ".";
                }
            }
        });
        mColorCheckBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mColorCheckBox4.isChecked()) {
                    cht = mColorCheckBox4.getText().toString() + " ";
                }else{
                    cht = ".";
                }
            }
        });
        mColorCheckBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mColorCheckBox5.isChecked()) {
                    pt = mColorCheckBox5.getText().toString() + " ";
                }else{
                    pt = ".";
                }
            }
        });
        mColorCheckBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mColorCheckBox6.isChecked()) {
                    sn= mColorCheckBox6.getText().toString() + " ";
                }else{
                    sn = ".";
                }
            }
        });
        mColorCheckBox7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mColorCheckBox7.isChecked()) {
                    st = mColorCheckBox7.getText().toString();
                }else{
                    st = ".";
                }
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

        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weekDays =  pn+vt+sr+cht+pt+sn+st;
                Bundle bundle = new Bundle();
                String everyDay = "Каждый день";
                weekDays = weekDays.replace(".", "");
                if (weekDays.equals("пн вт ср чт пт сб вс") ){
                    bundle.putString("week", weekDays);
                    bundle.putString("all", everyDay);
                }else{
                    bundle.putString("all", weekDays);
                    bundle.putString("week", weekDays);
                }
                SearchFragment fragobj = new SearchFragment();
                fragobj.setArguments(bundle);
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, fragobj);
                ft4.commit();
//                FragmentManager fm4 = getFragmentManager();
//                FragmentTransaction ft4= fm4.beginTransaction();
//                ft4.replace(R.id.fragment_container, new SearchFragment());
//                ft4.commit();
            }
        });

        return view;
    }
}