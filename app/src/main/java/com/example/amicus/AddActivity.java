package com.example.amicus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    LinearLayout autoChange;
    TextView text_auto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        autoChange = findViewById(R.id.autoChange);
        text_auto = findViewById(R.id.text_auto);

        if (getIntent().hasExtra("Autoname")) {
            text_auto.setText(getIntent().getStringExtra("Autoname"));
        }

        autoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,AutoChange.class);
                startActivity(intent);
                finish();
            }
        });
    }
}