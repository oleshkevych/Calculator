package com.example.vov4ik.moneycalculator.entity;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class Balance {

    private double cash;
    private double privat;
    private double rViza;
    private double rMaster;

    public Balance() {
    }

    public Balance(double cash, double privat, double rMaster, double rViza) {
        this.cash = cash;
        this.privat = privat;
        this.rMaster = rMaster;
        this.rViza = rViza;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getPrivat() {
        return privat;
    }

    public void setPrivat(double privat) {
        this.privat = privat;
    }

    public double getrMaster() {
        return rMaster;
    }

    public void setrMaster(double rMaster) {
        this.rMaster = rMaster;
    }

    public double getrViza() {
        return rViza;
    }

    public void setrViza(double rViza) {
        this.rViza = rViza;
    }
}
