package com.example.amicus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputLayout;


public class Registration extends AppCompatActivity {

    EditText your_name_textview;
    EditText number_textview;
    EditText password_create;
    EditText repeat_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        your_name_textview = findViewById(R.id.your_name_textview);
        number_textview = findViewById(R.id.login_email_et);
        password_create = findViewById(R.id.login_password_et);
        repeat_pass = findViewById(R.id.login_password_et_rewrite);
        ToggleButton toggleButton1 = findViewById(R.id.toggleButton1);
        TextView enter_link = findViewById(R.id.enter_link);
        Button continue_bt = findViewById(R.id.continue_bt);


        continue_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = your_name_textview.getText().toString();
                String phone = number_textview.getText().toString();
                String pass = password_create.getText().toString();
                String rep_pass =password_create.getText().toString();
                if (validateForm(password_create,repeat_pass) && toggleButton1.isChecked() && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)
                &&!TextUtils.isEmpty(pass) &&!TextUtils.isEmpty(rep_pass)) {
                    Intent intent = new Intent(Registration.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Registration.this, "Что то мы забыли", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView policy_of_conf = findViewById(R.id.policy_of_conf);
        policy_of_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public boolean validateForm(EditText password_create,EditText repeat_pass){

        if (!password_create.getText().toString().equals(repeat_pass.getText().toString())) {
            Toast.makeText(Registration.this, "Пароли не совпали", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }
}