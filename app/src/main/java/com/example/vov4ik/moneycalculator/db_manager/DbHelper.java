package com.example.vov4ik.moneycalculator.db_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vov4ik.moneycalculator.entity.Balance;
import com.example.vov4ik.moneycalculator.entity.Payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class DbHelper extends SQLiteOpenHelper {


    //region Declarations

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "Calculator.db";
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private final static String DROP_TABLE = "DROP TABLE IF EXISTS ";

    // Database table names
    private final static String BALANCE = "Balance";
    private final static String PAYMENTS = "Payments";

    // Database column names
    private final static String ID = "Id";
    private final static String PRIVATE = "Private";
    private final static String CASH = "Cash";
    private final static String RVIZA = "RViza";
    private final static String RMASTER = "RMaster";
    private final static String DATEUPDATE = "DateUpdate";
    private final static String ACTION = "Action";
    private final static String PAYEDFOR = "PayedFor";
    private final static String PAYDSUM = "PaidSum";
    private final static String PAYEDFROM = "PaidFrom";
    private final static String SENDTO = "SendTo";

    private SQLiteDatabase mDatabase;

    //endregion

    //region Common db operations

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDbIfNotExist(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createDbIfNotExist(db);
    }


    private static void createDbIfNotExist(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE + BALANCE + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                CASH + " DOUBLE, " +
                PRIVATE + " DOUBLE, " +
                RVIZA + " DOUBLE, " +
                RMASTER + " DOUBLE, " +
                DATEUPDATE + " LONG"+");");
        db.execSQL(CREATE_TABLE + PAYMENTS + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                PAYEDFOR + " TEXT, " +
                ACTION + " INTEGER, " +
                PAYDSUM + " DOUBLE, " +
                PAYEDFROM + " INTEGER, " +
                SENDTO + " INTEGER " + ");");
    }

    public void deleteDbPaymens() {
        mDatabase.execSQL(DROP_TABLE + PAYMENTS);
    }

    public void addPayment(Payment payment){
        createDbIfNotExist(mDatabase);

        ContentValues contentValues = new ContentValues();
        contentValues.put(PAYEDFOR, payment.getPayedFor());
        contentValues.put(ACTION, payment.getAction());
        contentValues.put(PAYDSUM, payment.getPayedSum());
        contentValues.put(PAYEDFROM, payment.getPayedFrom());
        contentValues.put(SENDTO, payment.getSentTo());
        mDatabase.insert(PAYMENTS, null, contentValues);
        mDatabase.close();
    }

    public void addBalance(Balance balance){
        createDbIfNotExist(mDatabase);

        ContentValues contentValues = new ContentValues();
        contentValues.put(CASH, balance.getCash());
        contentValues.put(PRIVATE, balance.getPrivat());
        contentValues.put(RVIZA, balance.getrViza());
        contentValues.put(RMASTER, balance.getrMaster());
        contentValues.put(DATEUPDATE, new Date().getTime());
        mDatabase.insert(BALANCE, null, contentValues);
        mDatabase.close();
    }

    public Balance getBalance(){
        createDbIfNotExist(mDatabase);

        Balance balance = new Balance();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + BALANCE, null);
        if(cursor.getCount() == 0){
            return null;
        }else{
            cursor.moveToLast();
            balance.setCash(cursor.getDouble(cursor.getColumnIndex(CASH)));
            balance.setPrivat(cursor.getDouble(cursor.getColumnIndex(PRIVATE)));
            balance.setrViza(cursor.getDouble(cursor.getColumnIndex(RVIZA)));
            balance.setrMaster(cursor.getDouble(cursor.getColumnIndex(RMASTER)));
        }
        cursor.close();
        mDatabase.close();
        return balance;
    }

    public long getDateUpdate(){
        createDbIfNotExist(mDatabase);

        long dateUpdate = 0;
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + BALANCE, null);
        if(cursor.getCount() > 0){
            cursor.moveToLast();
            dateUpdate = cursor.getLong(cursor.getColumnIndex(DATEUPDATE));
        }
        cursor.close();
        mDatabase.close();
        return dateUpdate;
    }

    public List<Payment> getPayments(){
        createDbIfNotExist(mDatabase);

        List<Payment> payments = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + PAYMENTS, null);
        if(cursor.getCount() == 0){
            return new ArrayList<>();
        }else{
            cursor.moveToFirst();
            do {
                Payment payment = new Payment();
                payment.setPayedSum(cursor.getDouble(cursor.getColumnIndex(PAYDSUM)));
                payment.setAction(cursor.getInt(cursor.getColumnIndex(ACTION)));
                payment.setPayedFrom(cursor.getInt(cursor.getColumnIndex(PAYEDFROM)));
                payment.setSentTo(cursor.getInt(cursor.getColumnIndex(SENDTO)));
                payment.setPayedFor(cursor.getString(cursor.getColumnIndex(PAYEDFOR)));
                payments.add(payment);
            }while(cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return payments;
    }

}
