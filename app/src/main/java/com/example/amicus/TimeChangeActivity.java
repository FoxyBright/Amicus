package com.example.amicus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimeChangeActivity  extends AppCompatActivity implements
        TimePicker.OnTimeChangedListener{

    private TimePicker timePicker;
    private Button btnSet;
    private TimePicker timePicker1;
    String timeFrom;
    String time_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_change);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(this);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker1.setOnTimeChangedListener(this);
        btnSet = (Button) findViewById(R.id.save_bt);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeFrom = timePicker.getHour() + ":" + timePicker.getMinute();
                time_to = timePicker1.getHour() + ":" + timePicker1.getMinute();
                Bundle bundle = new Bundle();
                bundle.putString("timeFrom", timeFrom);
                bundle.putString("timeTo", time_to);
                SearchFragment fragobj = new SearchFragment();
                fragobj.setArguments(bundle);
                finish();
            }
        });



    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
    }

    public String getFrom() {
        return timeFrom;
    }

    public String getTo() {
        return time_to;
    }
}