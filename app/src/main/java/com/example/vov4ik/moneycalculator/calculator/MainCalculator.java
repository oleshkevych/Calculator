package com.example.vov4ik.moneycalculator.calculator;

import android.content.Context;
import android.widget.Toast;

import com.example.vov4ik.moneycalculator.db_manager.DbConnector;
import com.example.vov4ik.moneycalculator.entity.Balance;
import com.example.vov4ik.moneycalculator.entity.Constants;
import com.example.vov4ik.moneycalculator.entity.Payment;

import java.util.List;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class MainCalculator {

    private Balance balance;
    private List<Payment> paymentList;
    private Context context;

    public Balance getBalance() {
        Balance balance = new Balance();
        balance.setrMaster(this.balance.getrMaster());
        balance.setCash(this.balance.getCash());
        balance.setrViza(this.balance.getrViza());
        balance.setPrivat(this.balance.getPrivat());
        for (Payment p : paymentList) {
            switch (p.getPayedFrom()){
                case 0:
                    balance = actionChecker(p, balance, 0);
                    break;
                case 1:
                    balance = actionChecker(p, balance, 1);
                    break;
                case 2:
                    balance = actionChecker(p, balance, 2);
                    break;
                case 3:
                    balance = actionChecker(p, balance, 3);
                    break;
            }

        }
        return balance;
    }
    private Balance actionChecker(Payment p, Balance b, int payedVariant){
        double d = 0;
        if(p.getAction() == Constants.getActionList().indexOf("Spend")){
            d -= p.getPayedSum();
        }else if(p.getAction() == Constants.getActionList().indexOf("Add")) {
            d += p.getPayedSum();
        } else if(p.getAction() == Constants.getActionList().indexOf("Send")) {
            d -= p.getPayedSum();
            switch (p.getSentTo()){
                case 0:
                    b.setCash(b.getCash()+p.getPayedSum());
                    break;
                case 1:
                    b.setPrivat(b.getPrivat()+p.getPayedSum());
                    break;
                case 2:
                    b.setrViza(b.getrViza()+p.getPayedSum());
                    break;
                case 3:
                    b.setrMaster(b.getrMaster()+p.getPayedSum());
                    break;
            }
        }

        switch (payedVariant){
            case 0:
                b.setCash(b.getCash()+d);
                break;
            case 1:
                b.setPrivat(b.getPrivat()+d);
                break;
            case 2:
                b.setrViza(b.getrViza()+d);
                break;
            case 3:
                b.setrMaster(b.getrMaster()+d);
                break;
        }


        return b;
    }

    public MainCalculator(Context context) {
        this.context = context;
        balance = DbConnector.getBalance(context);
        paymentList = DbConnector.getPayments(context);
    }

    public double getSpentForMonth() {
        double spent = 0;
        for (Payment p : paymentList) {
            if (p.getAction() == Constants.getActionList().indexOf("Spend")) {
                spent += p.getPayedSum();
            }
        }
        return spent;
    }

    public double getGotForMonth() {
        double got = 0;
        for (Payment p : paymentList) {
            if (p.getAction() == Constants.getActionList().indexOf("Add")) {
                got += p.getPayedSum();
            }
        }
        return got;
    }

    public double getBalanceForMonth() {
        double balance = 0;
        for (Payment p : paymentList) {
            if (p.getAction() == Constants.getActionList().indexOf("Add")) {
                balance += p.getPayedSum();
            } else if (p.getAction() == Constants.getActionList().indexOf("Spend")) {
                balance -= p.getPayedSum();
            }
        }
        return balance;
    }

    public void addPayment(Payment payment) {
        paymentList.add(payment);
        DbConnector.addPayment(context, payment);
        Toast.makeText(context, "Payment was added", Toast.LENGTH_LONG).show();
    }

    public void setBalance(Balance balance) {
        DbConnector.addBalance(context, balance);
    }
    //TODO: add year statistic
}
