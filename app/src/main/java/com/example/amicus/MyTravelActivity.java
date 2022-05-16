package com.example.amicus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;


public class MyTravelActivity extends AppCompatActivity {

    SampleFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travel);
        Button back_tab = findViewById(R.id.back_bt);
        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        back_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(),getBaseContext());
        adapter.addFragment(new PageFragment());
        adapter.addFragment(new PageFragment1());
        viewPager.setAdapter(adapter);
    }
}