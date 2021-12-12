package com.example.amicus;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

   BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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