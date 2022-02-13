package com.example.amicus;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.amicus.API.AuthorizationBody;
import com.example.amicus.API.AuthorizationResponce;
import com.example.amicus.API.RetrofitAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

   BottomNavigationView bottomNavigationView;
   static String phone;
   static String parol1;
   static String name1;
   static String facebook;
   static String pochta;
   static String truba;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Вы уверены что хотите выйти?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Нет", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        String name;
        String parol;

        if(arguments!=null){
            name = arguments.get("phone").toString();
            parol = arguments.getString("parol");
            SharedPreferences sp=getSharedPreferences("Login", 0);
            SharedPreferences.Editor Ed=sp.edit();
            Ed.putString("name",name );
            Ed.putString("parol",parol);
            Ed.commit();
        }

        SharedPreferences sp1=this.getSharedPreferences("Login",0);
        phone =sp1.getString("name", null);
        parol1 = sp1.getString("parol", null);

        RetrofitAPI api = RetrofitAPI.getInstance();

        AuthorizationBody body = new AuthorizationBody();
        body.phone = phone;
        body.password = parol1;
        api.getJSONApi().authUser(body);
        Call<AuthorizationResponce> call = api.getJSONApi().authUser(body);
        call.enqueue(new Callback<AuthorizationResponce>() {
            @Override
            public void onResponse(Call<AuthorizationResponce> call, Response<AuthorizationResponce> response) {
                Toast.makeText(MainActivity.this, "Запрос с главной активности " +response.body().getName(), Toast.LENGTH_SHORT).show();
                name1 = response.body().getName();
                facebook = response.body().getFacebook();
                pochta = response.body().getMail();
                truba = response.body().getPhone();
            }

            @Override
            public void onFailure(Call<AuthorizationResponce> call, Throwable t) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new SearchFragment());
        ft.commit();

        setContentView(R.layout.activity_main);

       bottomNavigationView = findViewById(R.id.bottom_navigation);
       bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.searchFragment:
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_container, new SearchFragment());
                        ft.commit();
                        break;
                    case R.id.addFragment:

                        FragmentManager fm1 = getFragmentManager();
                        FragmentTransaction ft1 = fm1.beginTransaction();
                        ft1.replace(R.id.fragment_container, new AddFragment());
                        ft1.commit();
                        break;
                    case R.id.MyTravelFragment:

                        FragmentManager fm2 = getFragmentManager();
                        FragmentTransaction ft2 = fm2.beginTransaction();
                        ft2.replace(R.id.fragment_container, new MyTravel());
                        ft2.commit();
                        break;
                    case R.id.navigation_chat:

                        FragmentManager fm3 = getFragmentManager();
                        FragmentTransaction ft3 = fm3.beginTransaction();
                        ft3.replace(R.id.fragment_container, new ChatFragment());
                        ft3.commit();
                        break;
                    case R.id.profileFragment:

                        FragmentManager fm4 = getFragmentManager();
                        FragmentTransaction ft4= fm4.beginTransaction();
                        ft4.replace(R.id.fragment_container, new ProfileFragment());
                        ft4.commit();
                        break;
                }

                return true;
            }
        });

    }
}