package com.example.amicus;

import static com.example.amicus.API.RetrofitAPI.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amicus.API.AuthorizationBody;
import com.example.amicus.API.AuthorizationResponce;
import com.example.amicus.API.JSONPlaceHolderApi;
import com.example.amicus.API.RetrofitAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout login_email_et;
    TextInputLayout very_code;
    private TextView tw_phone;
    private TextView code;
    private EditText number_textview;
    private EditText code_veryfic;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember","true");
        editor.apply();
        Toast.makeText(LoginActivity.this, "Запомнили", Toast.LENGTH_SHORT).show();




        TextView registration_link = findViewById(R.id.registration_link);
        tw_phone = findViewById(R.id.tw_phone);
        number_textview = findViewById(R.id.number_textview);
        very_code = findViewById(R.id.very_code);
        code = findViewById(R.id.code);
        login_email_et = findViewById(R.id.login_email_et);
        code_veryfic = findViewById(R.id.code_veryfic);
        Button login_bt = findViewById(R.id.login_bt);



        registration_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Registration.class);
                startActivity(intent);
            }
        });

        registration_link.setPaintFlags(registration_link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitAPI api = RetrofitAPI.getInstance();

                AuthorizationBody body = new AuthorizationBody();
                body.phone = number_textview.getText().toString();
                body.password = code_veryfic.getText().toString();
                api.getJSONApi().authUser(body);
                Call<AuthorizationResponce> call = api.getJSONApi().authUser(body);
                call.enqueue(new Callback<AuthorizationResponce>() {
                    @Override
                    public void onResponse(Call<AuthorizationResponce> call, Response<AuthorizationResponce> response) {
                        Toast.makeText(LoginActivity.this, "Добро пожаловать, " +response.body().getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("phone", body.phone);
                        intent.putExtra("parol", body.password);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<AuthorizationResponce> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
            //TODO ГАЛОЧКА
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (compoundButton.isChecked()) {
//                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("remember","true");
//                    editor.apply();
//                    Toast.makeText(LoginActivity.this, "Запомнили", Toast.LENGTH_SHORT).show();
//
//                }else if(!compoundButton.isChecked()){
//                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("remember","false");
//                    editor.apply();
//                    Toast.makeText(LoginActivity.this, "не запомнили", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


     }
}