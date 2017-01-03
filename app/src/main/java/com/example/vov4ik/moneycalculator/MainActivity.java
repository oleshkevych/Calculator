package com.example.vov4ik.moneycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.vov4ik.moneycalculator.calculator.MainCalculator;
import com.example.vov4ik.moneycalculator.fragments.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static MainCalculator mMainCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainCalculator = new MainCalculator(getApplicationContext());

        if(mMainCalculator.getBalance() == null){
            Intent intent = new Intent(this, FirstStartAppActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_main);
        PagerAdapter mSectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }

    public static MainCalculator getMainCalculator() {
        return mMainCalculator;
    }

}
