package com.example.amicus.ui;




import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amicus.LoginActivity;
import com.example.amicus.MainActivity;
import com.example.amicus.R;
import com.google.firebase.auth.FirebaseAuth;

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
        initFunctionality();
    }


    private void initFunctionality()
        {
            layout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                SharedPreferences prefs = getSharedPreferences("remember", MODE_PRIVATE);
                boolean rem = prefs.getBoolean("remember", false);
                if (rem) {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_DURATION);
    }


}
