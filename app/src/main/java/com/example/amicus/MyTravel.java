package com.example.amicus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MyTravel extends Fragment {

    SampleFragmentPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_travel, container, false);
        Button back_tab = view.findViewById(R.id.back_tab);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        back_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO варианты убить фрагмент
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().beginTransaction().remove(MyTravel.this).commit();
            }
        });

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SampleFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity().getBaseContext());
        adapter.addFragment(new PageFragment());
        adapter.addFragment(new PageFragment1());
        viewPager.setAdapter(adapter);
    }

}
