package com.sv.test_task.feature.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.sv.test_task.R;
import com.sv.test_task.feature.Cart;
import com.sv.test_task.feature.adapter.RowProductAdapter;
import com.sv.test_task.feature.listener.CartListener;

import java.text.NumberFormat;

public class CartDialog extends BottomSheetDialogFragment {
    private CartListener listener;

    private RecyclerView list;
    private View viewNone, viewInfo;
    private TextView info;
    private MaterialButton btnPay, btnClear;

    public void setListener(CartListener listener) {
        this.listener = listener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cart, container,
                false);
        btnClear = view.findViewById(R.id.btnClear);
        list = view.findViewById(R.id.viewList);
        viewNone = view.findViewById(R.id.viewNone);
        viewInfo = view.findViewById(R.id.viewInfo);
        info = view.findViewById(R.id.info);
        btnPay = view.findViewById(R.id.btnPay);

        if (Cart.count() > 0) showList();
        else showViewNone();

        return view;
    }

    private void showList() {
        viewNone.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        viewInfo.setVisibility(View.VISIBLE);
        btnPay.setVisibility(View.VISIBLE);
        btnClear.setVisibility(View.VISIBLE);

        btnClear.setOnClickListener(v -> clear());
        btnPay.setOnClickListener(v -> done());


        NumberFormat f = NumberFormat.getNumberInstance();
        f.setMinimumFractionDigits(2);
        f.setMaximumFractionDigits(2);

        info.setText(f.format(Cart.getFinalPrice())+" ₽");

        list.setAdapter(new RowProductAdapter());

    }

    private void clear(){
        dismiss();
        listener.onClear();
    }

    private void done(){
        dismiss();
        listener.onPay();
    }

    private void showViewNone() {
        viewNone.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
        viewInfo.setVisibility(View.GONE);
        btnPay.setVisibility(View.GONE);
        btnClear.setVisibility(View.GONE);


    }
}
