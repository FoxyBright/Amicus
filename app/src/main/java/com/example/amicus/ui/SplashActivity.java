package com.example.amicus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amicus.MainActivity;
import com.example.amicus.R;

public class SplashActivity extends AppCompatActivity {

    private LinearLayout layout;
    private ImageView image;
    private TextView text;

    private static final int SPLASH_DURATION = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        layout = findViewById(R.id.splash);
    }


    private void initFunctionality()
        {
            layout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }

    protected void onResume()
    {
        super.onResume();
        initFunctionality();
    }


}
