package com.example.amicus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    LinearLayout autoChange;
    TextView text_auto;

    TextView timeto;
    TextView timeFrom;
    TextView week;
    TextView pass;
    TextView weeks1;
    EditText departurePlace;
    EditText arrivalPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        departurePlace = findViewById(R.id.departurePlace);
        arrivalPlace = findViewById(R.id.arrivalPlace);
        timeFrom = findViewById(R.id.timefrom);
        timeto = findViewById(R.id.timeto);
        pass = findViewById(R.id.pass);
        week = findViewById(R.id.week);
        text_auto = findViewById(R.id.text_auto);
        autoChange = findViewById(R.id.autoChange);


        if (getIntent().hasExtra("Autoname")) {
            text_auto.setText(getIntent().getStringExtra("Autoname"));
        }

        LinearLayout calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new DayChangeFragment());
                ft4.commit();
            }
        });

        LinearLayout time = findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new TimeChangeFragment());
                ft4.commit();
            }
        });

        LinearLayout people = findViewById(R.id.people);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new CountPassagersFragment());
                ft4.commit();
            }
        });

        autoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}