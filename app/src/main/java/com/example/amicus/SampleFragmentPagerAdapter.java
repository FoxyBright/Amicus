package com.example.amicus;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;


import java.util.ArrayList;
import java.util.List;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private String tabTitles[] = new String[] { "Водитель", "Пассажир" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override public int getCount() {
        return mFragmentList.size() ;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override public CharSequence getPageTitle(int position) {
        // генерируем заголовок в зависимости от позиции
        return tabTitles[position];
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public void delFragment(Fragment fragment) {
        FragmentManager manager = ((Fragment) fragment).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) fragment);
        trans.commit();
    }
}
