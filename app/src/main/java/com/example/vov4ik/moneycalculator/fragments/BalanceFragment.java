package com.example.vov4ik.moneycalculator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vov4ik.moneycalculator.MainActivity;
import com.example.vov4ik.moneycalculator.R;
import com.example.vov4ik.moneycalculator.entity.Balance;


/**
 * Created by vov4ik on 1/2/2017.
 */
public class BalanceFragment extends Fragment{

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.balance, container, false);

        setBalance();
        return rootView;
    }
    public void setBalance(){
        Balance balance = MainActivity.getMainCalculator().getBalance();
        TextView cashBalance = (TextView)rootView.findViewById(R.id.cashBalance);
        cashBalance.setText(String.valueOf(balance.getCash()));

        TextView privateBalance = (TextView)rootView.findViewById(R.id.privateBalance);
        privateBalance.setText(String.valueOf(balance.getPrivat()));

        TextView raiffeisenVizaBalance = (TextView)rootView.findViewById(R.id.raiffeisenVizaBalance);
        raiffeisenVizaBalance.setText(String.valueOf(balance.getrViza()));

        TextView raiffeisenMasterBalance = (TextView)rootView.findViewById(R.id.raiffeisenMasterBalance);
        raiffeisenMasterBalance.setText(String.valueOf(balance.getrMaster()));
    }
}
