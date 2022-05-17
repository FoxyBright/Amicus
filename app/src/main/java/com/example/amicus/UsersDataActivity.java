package com.example.amicus;

import static com.example.amicus.TravelAdapter.author;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.API.GetUserData;
import com.example.amicus.API.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersDataActivity extends AppCompatActivity {

    ImageView profile_image;
    TextView profile_name;
    TextView email_name;
    TextView regdata;
    TextView facebook;
    TextView phone_edit;
    Button call_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_data);

        Button back_btn = findViewById(R.id.back_btn);
        profile_image = findViewById(R.id.profile_image);
        profile_name = findViewById(R.id.profile_name);
        email_name = findViewById(R.id.email_name);
        regdata = findViewById(R.id.regdata);
        facebook = findViewById(R.id.facebook);
        phone_edit = findViewById(R.id.phone_edit);
        call_button = findViewById(R.id.call_bt);

        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ phone_edit.getText().toString()));
                startActivity(intent);
            }
        });

        RetrofitAPI api = RetrofitAPI.getInstance();
        Call<GetUserData> call = api.getJSONApi().getUsData(author);
        call.enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(Call<GetUserData> call, Response<GetUserData> response) {
                GetUserData getUserData = response.body();
                profile_name.setText(getUserData.getName());
                email_name.setText(getUserData.getMail());
                regdata.setText(getUserData.getRegdata());
                facebook.setText(getUserData.getFacebook());
                phone_edit.setText(getUserData.getPhone());
                Glide.with(UsersDataActivity.this).load(getUserData.getPhoto()).diskCacheStrategy(DiskCacheStrategy.NONE).into(profile_image);
            }

            @Override
            public void onFailure(Call<GetUserData> call, Throwable t) {

            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersDataActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}