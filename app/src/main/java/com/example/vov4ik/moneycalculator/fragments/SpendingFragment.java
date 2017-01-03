package com.example.vov4ik.moneycalculator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vov4ik.moneycalculator.MainActivity;
import com.example.vov4ik.moneycalculator.R;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class SpendingFragment extends Fragment{

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.spendings, container, false);
        return rootView;
    }

    public void setNewText(){
        TextView spentMonth = (TextView)rootView.findViewById(R.id.spentMoneyThisMonth);
        spentMonth.setText(String.valueOf(MainActivity.getMainCalculator().getSpentForMonth()));

        TextView gotMonth = (TextView)rootView.findViewById(R.id.gotMoneyThisMonth);
        gotMonth.setText(String.valueOf(MainActivity.getMainCalculator().getGotForMonth()));

        TextView totalMonth = (TextView)rootView.findViewById(R.id.totalForMonth);
        totalMonth.setText(String.valueOf(MainActivity.getMainCalculator().getBalanceForMonth()));

        TextView spentYear = (TextView)rootView.findViewById(R.id.spentMoney2017);
        spentYear.setText(String.valueOf(MainActivity.getMainCalculator().getSpentForMonth()));

        TextView gotYear = (TextView)rootView.findViewById(R.id.gotMoney2017);
        gotYear.setText(String.valueOf(MainActivity.getMainCalculator().getGotForMonth()));

        TextView totalYear = (TextView)rootView.findViewById(R.id.balanceForYear);
        totalYear.setText(String.valueOf(MainActivity.getMainCalculator().getBalanceForMonth()));
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(menuVisible){
           setNewText();
        }
    }
}
