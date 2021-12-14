package com.example.amicus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.amicus.ui.ConfirmationActivity;

public class Registration extends AppCompatActivity {

    private TextView enter_link;
    private Button continue_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView enter_link = findViewById(R.id.enter_link);
        Button continue_bt = findViewById(R.id.continue_bt);

        continue_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, ConfirmationActivity.class);
                startActivity(intent);
            }
        });

        TextView policy_of_conf = findViewById(R.id.policy_of_conf);
        policy_of_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        continue_bt.setClickable(false);

        ToggleButton toggleButton1 = findViewById(R.id.toggleButton1);
        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton1.isChecked()){
                    continue_bt.setClickable(true);
                }
                else{
                    continue_bt.setClickable(false);
                }
            }
        });

        policy_of_conf.setPaintFlags(policy_of_conf.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        enter_link.setPaintFlags(enter_link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        enter_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}