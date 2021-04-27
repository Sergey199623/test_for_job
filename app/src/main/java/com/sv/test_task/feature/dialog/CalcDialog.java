package com.sv.test_task.feature.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.sv.test_task.R;
import com.sv.test_task.feature.listener.CartListener;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;

public class CalcDialog extends DialogFragment implements View.OnClickListener {

    private CartListener listener;
    private float count, price;
    private String name;

    private TextView viewName, viewInfo;
    private EditText inputText;

    public CalcDialog(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public void setListener(CartListener listener) {
        this.listener = listener;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(),R.style.Theme_AppCompat_Light_Dialog);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_calc, null);
        dialogBuilder.setView(dialogView);
        viewName = dialogView.findViewById(R.id.name);
        viewInfo = dialogView.findViewById(R.id.info);
        inputText = dialogView.findViewById(R.id.input);
        viewName.setText(name);

        initBtns(dialogView);
        clear();

        return dialogBuilder.create();
    }

    private void clear(){
        count =0;
        inputText.setText("0");
        viewInfo.setText("0,00 ₽ / 0,00 КГ");

    }

    private void done(){
        if(count>0){

            listener.onAddRow(name,count,price);
            dismiss();
        }
    }

    private void initBtns(View root){
        root.findViewById(R.id.btnCancel).setOnClickListener(v -> dismiss());
        root.findViewById(R.id.btnDone).setOnClickListener(v -> done());

        root.findViewById(R.id.btn1).setOnClickListener(this);
        root.findViewById(R.id.btn2).setOnClickListener(this);
        root.findViewById(R.id.btn3).setOnClickListener(this);

        root.findViewById(R.id.btn4).setOnClickListener(this);
        root.findViewById(R.id.btn5).setOnClickListener(this);
        root.findViewById(R.id.btn6).setOnClickListener(this);

        root.findViewById(R.id.btn7).setOnClickListener(this);
        root.findViewById(R.id.btn8).setOnClickListener(this);
        root.findViewById(R.id.btn9).setOnClickListener(this);

        root.findViewById(R.id.btnDiv).setOnClickListener(this);
        root.findViewById(R.id.btn0).setOnClickListener(this);
        root.findViewById(R.id.btnCl).setOnClickListener(this);
    }

    private void analyze(String str){
        str = str.replace(",",".");
        if(str.equalsIgnoreCase(".")) str ="0";
        count  = Float.parseFloat(str);

        NumberFormat f = NumberFormat.getNumberInstance();
        f.setMinimumFractionDigits(2);
        f.setMaximumFractionDigits(2);

        viewInfo.setText(f.format(price*count)+" ₽ / "+f.format(count)+" КГ");
    }


    @Override
    public void onClick(View v) {
        int size = inputText.getText().length();
        int dp = inputText.getText().toString().indexOf(",");
        String oldStr = inputText.getText().toString();
        switch (v.getId()){
            default:
                boolean many = false;
                if(dp<0){
                    if(size<3 || v.getId()==R.id.btnDiv) many = true;
                } else if(size-dp<3) many = true;

                if(many) {
                    if(oldStr.equalsIgnoreCase("0") && v.getId()!=R.id.btnDiv) oldStr="";
                    inputText.setText(oldStr+((MaterialButton)v).getText());
                    analyze(inputText.getText().toString());
                }
                break;
            case R.id.btnCl:
                clear();
                break;
        }

    }
}
