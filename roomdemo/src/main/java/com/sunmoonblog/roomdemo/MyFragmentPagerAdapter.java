package com.sunmoonblog.roomdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new PersonListFragment();

            case 1:
                return new CalendarFragment();

            default:
                return new CollectActivityFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
