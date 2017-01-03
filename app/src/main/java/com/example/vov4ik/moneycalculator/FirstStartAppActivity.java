package com.example.vov4ik.moneycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vov4ik.moneycalculator.calculator.MainCalculator;
import com.example.vov4ik.moneycalculator.entity.Balance;

public class FirstStartAppActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start_app);
        final Button confirm = (Button) findViewById(R.id.confirmFirstStartButton);
        assert confirm != null;
        confirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                confirm.setBackground(getResources().getDrawable(R.drawable.confirm_button_click));
                return false;
            }
        });
        confirm.setOnClickListener(this);
    }

    @Override
     public void onClick(View v) {
        v.setBackground(getResources().getDrawable(R.drawable.confirm_button_start));
        EditText cashText = (EditText) findViewById(R.id.startCashBalance);
        assert cashText != null;

        EditText privatText = (EditText) findViewById(R.id.startPrivatBalance);
        assert privatText != null;

        EditText rVizaText = (EditText) findViewById(R.id.startRVizaBalance);
        assert rVizaText != null;

        EditText rMasterText = (EditText) findViewById(R.id.startRMasterBalance);
        assert rMasterText != null;
        if (cashText.getText().toString().length() > 0 &&
                privatText.getText().toString().length() > 0 &&
                rVizaText.getText().toString().length() > 0 &&
                rMasterText.getText().toString().length() > 0){

            double cash = Double.valueOf(cashText.getText().toString());
            double privat = Double.valueOf(privatText.getText().toString());
            double rViza = Double.valueOf(rVizaText.getText().toString());
            double rMaster = Double.valueOf(rMasterText.getText().toString());
            Balance balance = new Balance(cash, privat, rMaster, rViza);
            MainCalculator mainCalculator = new MainCalculator(getApplicationContext());
            mainCalculator.setBalance(balance);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_LONG).show();
        }
    }
}
