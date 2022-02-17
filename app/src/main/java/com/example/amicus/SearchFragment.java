package com.example.amicus;

import static android.content.Context.MODE_PRIVATE;

import android.accounts.AccountManager;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SearchFragment extends Fragment {

    TextView timeto;
    TextView timeFrom;
    TextView pass;
    final static String SHARED_NAME_STRING="sharedp";
    final static String USER_NAME_STRING="user";
    final static String USER_NAME_STRING1="user1";
    final static String USER_NAME_STRING2="user2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String from = getArguments().getString("timeFrom");
            String to = getArguments().getString("timeTo");
            String pass1 = getArguments().getString("pass");

            SharedPreferences.Editor editor = getContext().getSharedPreferences(SHARED_NAME_STRING, MODE_PRIVATE).edit();
            SharedPreferences prefs = getContext().getSharedPreferences(SHARED_NAME_STRING, MODE_PRIVATE);


            if (pass1 != null) {
                if (from != null && to !=null) {
                    editor.putString(USER_NAME_STRING1, from);
                    editor.putString(USER_NAME_STRING2, to);
                    editor.apply();
                }
                editor.putString(USER_NAME_STRING, pass1);
                editor.apply();
            }
            // To load the data at a later time
            String loadedString = prefs.getString(USER_NAME_STRING, null);
            pass= view.findViewById(R.id.pass);
            pass.setText(loadedString);
            if (from != null && to !=null) {
                String loadedString1 = prefs.getString(USER_NAME_STRING1, null);
                String loadedStrin21 = prefs.getString(USER_NAME_STRING2, null);
                timeFrom= view.findViewById(R.id.timefrom);
                timeto= view.findViewById(R.id.timeto);
                timeFrom.setText(loadedString1);
                timeto.setText(loadedStrin21);
            }

        }

        LinearLayout calendar = view.findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new DayChangeFragment());
                ft4.commit();
            }
        });

        LinearLayout time = view.findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new TimeChangeFragment());
                ft4.commit();
            }
        });

        LinearLayout people = view.findViewById(R.id.people);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new CountPassagersFragment());
                ft4.commit();
            }
        });

        Button search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new SearchResultFragment());
                ft4.commit();
            }
        });





        return view;
    }

}
