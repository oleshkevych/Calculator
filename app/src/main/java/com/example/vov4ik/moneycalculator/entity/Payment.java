package com.example.vov4ik.moneycalculator.entity;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class Payment {

    private String payedFor;
    private Double payedSum;
    private int action;
    private int payedFrom;
    private int sentTo;

    public Payment() {
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getPayedFor() {
        return payedFor;
    }

    public void setPayedFor(String payedFor) {
        this.payedFor = payedFor;
    }

    public int getPayedFrom() {
        return payedFrom;
    }

    public void setPayedFrom(int payedFrom) {
        this.payedFrom = payedFrom;
    }

    public int getSentTo() {
        return sentTo;
    }

    public void setSentTo(int sentTo) {
        this.sentTo = sentTo;
    }

    public Double getPayedSum() {
        return payedSum;
    }

    public void setPayedSum(Double payedSum) {
        this.payedSum = payedSum;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "action='" + action + '\'' +
                ", payedFor='" + payedFor + '\'' +
                ", payedSum=" + payedSum +
                ", payedFrom='" + payedFrom + '\'' +
                ", sentTo='" + sentTo + '\'' +
                '}';
    }
}
