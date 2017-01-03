package com.example.vov4ik.moneycalculator.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("Test", "Item " + position);

        switch (position % 3) {
            case 0:
                return new AddMoneyFragment();
            case 1:
                return new SpendingFragment();
            case 2:
                return new BalanceFragment();
            default:
                return new AddMoneyFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
