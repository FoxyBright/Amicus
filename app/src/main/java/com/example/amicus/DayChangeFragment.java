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

    static boolean pn1 = true;
    static boolean vt1 = true;
    static boolean sr1 = true;
    static boolean cht1 = true;
    static boolean pt1 = true;
    static boolean sb1 = true;
    static boolean vs1 = true;



    static String weekDays;


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

        if (pn1 == true){
            mColorCheckBox.isChecked();
        } else {
            mColorCheckBox.setChecked(false);
        }
        if (vt1 == true){
            mColorCheckBox2.isChecked();
        } else {
            mColorCheckBox2.setChecked(false);
        }
        if (sr1 == true){
            mColorCheckBox3.isChecked();
        } else {
            mColorCheckBox3.setChecked(false);
        }
        if (cht1 == true){
            mColorCheckBox4.isChecked();
        } else {
            mColorCheckBox4.setChecked(false);
        }
        if (pt1 == true){
            mColorCheckBox5.isChecked();
        } else {
            mColorCheckBox5.setChecked(false);
        }
        if (sb1 == true){
            mColorCheckBox6.isChecked();
        } else {
            mColorCheckBox6.setChecked(false);
        }
        if (vs1 == true){
            mColorCheckBox7.isChecked();
        } else {
            mColorCheckBox7.setChecked(false);
        }




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

                if (mColorCheckBox.isChecked()) {
                    pn = mColorCheckBox.getText().toString() + " ";
                    pn1 = true;
                }else{
                    pn = ".";
                    pn1 = false;
                }
                if (mColorCheckBox2.isChecked()) {
                    vt = mColorCheckBox2.getText().toString() + " ";
                    vt1 = true;
                }else{
                    vt = ".";
                    vt1 = false;
                }
                if (mColorCheckBox3.isChecked()) {
                    sr = mColorCheckBox3.getText().toString() + " ";
                    sr1 = true;
                }else{
                    sr = ".";
                    sr1 = false;
                }
                if (mColorCheckBox4.isChecked()) {
                    cht = mColorCheckBox4.getText().toString() + " ";
                    cht1 = true;
                }else{
                    cht = ".";
                    cht1 = false;
                }
                if (mColorCheckBox5.isChecked()) {
                    pt = mColorCheckBox5.getText().toString() + " ";
                    pt1 = true;
                }else{
                    pt = ".";
                    pt1 = false;
                }
                if (mColorCheckBox6.isChecked()) {
                    sn= mColorCheckBox6.getText().toString() + " ";
                    sb1 = true;
                }else{
                    sn = ".";
                    sb1 = false;
                }
                if (mColorCheckBox7.isChecked()) {
                    st = mColorCheckBox7.getText().toString();
                    vs1 = true;
                }else{
                    st = ".";
                    vs1 = false;
                }

                weekDays =  pn+vt+sr+cht+pt+sn+st;

                weekDays = weekDays.replace(".", "");

                if (weekDays.equals("пн вт ср чт пт сб вс")) {
                    weekDays = "Каждый день";
                }

                SearchFragment fragobj = new SearchFragment();
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, fragobj);
                ft4.commit();
            }
        });

        return view;
    }
}