package com.example.vov4ik.moneycalculator.db_manager;

import android.content.Context;

import com.example.vov4ik.moneycalculator.entity.Balance;
import com.example.vov4ik.moneycalculator.entity.Payment;

import java.util.List;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class DbConnector {

    public static void addPayment(Context context, Payment payment){
        new DbHelper(context).addPayment(payment);
    }

    public static void addBalance(Context context, Balance balance){
        new DbHelper(context).deleteDbPaymens();
        new DbHelper(context).addBalance(balance);
    }

    public static Balance getBalance(Context context){
        return new DbHelper(context).getBalance();
    }

    public static long getDateUpdate(Context context){
        return new DbHelper(context).getDateUpdate();
    }

    public static List<Payment> getPayments(Context context){
        return new DbHelper(context).getPayments();
    }
}
