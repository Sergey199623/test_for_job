package com.sv.test_task.feature.listener;

public interface CartListener {

    void onClear();
    void onAddRow(String name, float count, float price);
    void onPay();
}
