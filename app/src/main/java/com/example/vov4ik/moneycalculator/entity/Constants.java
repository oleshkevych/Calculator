package com.example.vov4ik.moneycalculator.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class Constants {

    private static String[] itemsAction = new String[]{"Spend", "Add", "Send"};
    private static String[] itemsPayVariant = new String[]{"Cash", "Privat", "Raiffeisen Viza", "Raiffeisen MasterCard"};
    private static List<String> actionList = new ArrayList<>(Arrays.asList(itemsAction));
    private static List<String> payVariantList = new ArrayList<>(Arrays.asList(itemsPayVariant));
    public static String[] getItemsAction() {
        return itemsAction;
    }

    public static String[] getItemsPayVariant() {
        return itemsPayVariant;
    }

    public static List<String> getActionList() {
        return actionList;
    }

    public static List<String> getPayVariantList() {
        return payVariantList;
    }
}
