package com.example.amicus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.amicus.API.RegistrationBody;
import com.example.amicus.API.RegistrationResponce;
import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.messages.MemoryData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.PrintWriter;
import java.io.StringWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Registration extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
            "https://amicus-f81c5-default-rtdb.firebaseio.com");

    EditText your_name_textview;
    EditText number_textview;
    EditText password_create;
    EditText repeat_pass;
    EditText email;

    String name;
    String mobile;
    String email1;
    static String mobilTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        your_name_textview = findViewById(R.id.your_name_textview);
        number_textview = findViewById(R.id.login_email_et);
        password_create = findViewById(R.id.login_password_et);
        email = findViewById(R.id.email);
        repeat_pass = findViewById(R.id.login_password_et_rewrite);
        ToggleButton toggleButton1 = findViewById(R.id.toggleButton1);
        TextView enter_link = findViewById(R.id.enter_link);
        Button continue_bt = findViewById(R.id.continue_bt);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Загрузка...");

        continue_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                name = your_name_textview.getText().toString();
                mobile = number_textview.getText().toString();
                String pass = password_create.getText().toString();
                mobilTxt = password_create.getText().toString();
                String rep_pass =password_create.getText().toString();
                email1 = email.getText().toString();


                if (validateForm(password_create,repeat_pass) && toggleButton1.isChecked() && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(mobile)
                &&!TextUtils.isEmpty(pass) &&!TextUtils.isEmpty(rep_pass)) {

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        progressDialog.dismiss();
                        if (snapshot.child("users").hasChild(mobile)) {
                            Toast.makeText(Registration.this, "Мобилка уже есть в Firebase", Toast.LENGTH_SHORT).show();
                        }else{
                            databaseReference.child("users").child(mobile).child("email").setValue(email1);
                            databaseReference.child("users").child(mobile).child("name").setValue(name);
                            databaseReference.child("users").child(mobile).child("profile_pic").setValue("");

                            MemoryData.saveName(name,Registration.this);
                            MemoryData.savePhone(mobile,Registration.this);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });


                    RetrofitAPI api = RetrofitAPI.getInstance();
                    RegistrationBody body = new RegistrationBody();
                    body.name = name;
                    body.phone = mobile;
                    body.password = pass;
                    body.email = email1;
                    api.getJSONApi().regUser(body);
                    Call<RegistrationResponce> call = api.getJSONApi().regUser(body);
                    call.enqueue(new Callback<RegistrationResponce>() {
                        @Override
                        public void onResponse(Call<RegistrationResponce> call, Response<RegistrationResponce> response) {
                            Toast.makeText(Registration.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Registration.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<RegistrationResponce> call, Throwable t) {
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw);
                            t.printStackTrace(pw);
                            String error = pw.toString();
                            Toast.makeText(Registration.this, error, Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(Registration.this, "Что то мы забыли", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
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