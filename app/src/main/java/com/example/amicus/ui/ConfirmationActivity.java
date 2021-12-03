package com.example.amicus.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.amicus.LoginActivity;
import com.example.amicus.MainActivity;
import com.example.amicus.R;
import com.example.amicus.Registration;

import java.util.Timer;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView message_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView message_textview = findViewById(R.id.message_textview);




        message_textview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (message_textview.length() >= 8){
                    Intent intent = new Intent(ConfirmationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


// ТАЙМЕР!!!


//        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//            @Override
//            public void onChronometerTick(Chronometer chronometer) {
//                long elapsedMillis = SystemClock.elapsedRealtime()
//                        - mChronometer.getBase();
//
//                if (elapsedMillis > 5000) {
//                    String strElapsedMillis = "Прошло больше 5 секунд";
//                    Toast.makeText(getApplicationContext(),
//                            strElapsedMillis, Toast.LENGTH_SHORT)
//                            .show();
//                }
//            }
//        });
//
//        public void onStartClick(View view) {
//            mChronometer.setBase(SystemClock.elapsedRealtime());
//            mChronometer.start();
//        }
//
//        public void onStopClick(View view) {
//            mChronometer.stop();
//        }
//
//        public void onResetClick(View view) {
//            mChronometer.setBase(SystemClock.elapsedRealtime());
//        }
//
//
//        http://developer.alexanderklimov.ru/android/views/chronometer.php







    }
}