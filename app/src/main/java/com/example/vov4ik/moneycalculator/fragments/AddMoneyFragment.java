package com.example.vov4ik.moneycalculator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vov4ik.moneycalculator.MainActivity;
import com.example.vov4ik.moneycalculator.R;
import com.example.vov4ik.moneycalculator.entity.Constants;
import com.example.vov4ik.moneycalculator.entity.Payment;

/**
 * Created by vov4ik on 1/2/2017.
 */
public class AddMoneyFragment extends Fragment implements View.OnClickListener /*implements AdapterView.OnItemSelectedListener*/ {

    private Spinner dropdownAction;
    private Spinner dropdownPayedBy;
    private Spinner dropdownSendTo;
    private View rootView;
    private TextView spentTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.add_number, container, false);
        initSpinners();

        final Button confirmButton = (Button) rootView.findViewById(R.id.confirmButton);
        confirmButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                confirmButton.setBackground(getResources().getDrawable(R.drawable.confirm_button_click));
                return false;
            }
        });
        confirmButton.setOnClickListener(this);

        spentTextView = (TextView)rootView.findViewById(R.id.spentForThisMonth);
        setSpendValue();
        return rootView;
    }

    private void initSpinners(){

        dropdownAction = (Spinner)rootView.findViewById(R.id.spinnerAction);
        ArrayAdapter<String> adapterAction = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, Constants.getItemsAction());
        dropdownAction.setAdapter(adapterAction);
        dropdownAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    rootView.findViewById(R.id.sendToLayout).setVisibility(View.VISIBLE);
                }else{
                    rootView.findViewById(R.id.sendToLayout).setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dropdownPayedBy = (Spinner)rootView.findViewById(R.id.spinnerPayFrom);
        ArrayAdapter<String> adapterPayedBy = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, Constants.getItemsPayVariant());
        dropdownPayedBy.setAdapter(adapterPayedBy);
//        dropdownPayedBy.setOnItemSelectedListener(this);

        dropdownSendTo = (Spinner)rootView.findViewById(R.id.spinnerSendTo);
        ArrayAdapter<String> adapterSendTo = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, Constants.getItemsPayVariant());
        dropdownSendTo.setAdapter(adapterSendTo);
//        dropdownSendTo.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        v.setBackground(getResources().getDrawable(R.drawable.confirm_button_start));
        EditText payedForEditText = (EditText)rootView.findViewById(R.id.payedFor);
        String payedFor = payedForEditText.getText().toString();
        EditText payedSumText = (EditText)rootView.findViewById(R.id.numberOfMoney);
        String payedSum = payedSumText.getText().toString();
//        new DbHelper(getContext()).deleteDbPaymens();
        if(payedFor.length()>0 && payedSum.length()>0) {
            Payment payment = new Payment();
            payment.setPayedFor(payedFor);
            payment.setPayedSum(Double.parseDouble(payedSum));
            payment.setAction(dropdownAction.getSelectedItemPosition());
            payment.setPayedFrom(dropdownPayedBy.getSelectedItemPosition());
            if(rootView.findViewById(R.id.sendToLayout).getVisibility() == View.VISIBLE) {
                payment.setSentTo(dropdownSendTo.getSelectedItemPosition());
            }else{
                payment.setSentTo(-1);
            }
            MainActivity.getMainCalculator().addPayment(payment);
            setSpendValue();
            payedForEditText.setText("");
            payedSumText.setText("");
            Log.d("Test", payment.toString());
        }

    }


    public void setSpendValue(){
        spentTextView.setText(String.valueOf(MainActivity.getMainCalculator().getSpentForMonth()));
    }
}
