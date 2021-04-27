package com.sv.test_task.feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.andremion.counterfab.CounterFab;
import com.google.android.material.tabs.TabLayout;
import com.sv.test_task.R;
import com.sv.test_task.feature.adapter.GoodsPagerAdapter;
import com.sv.test_task.feature.dialog.CartDialog;
import com.sv.test_task.feature.listener.CartListener;

public class GoodsFragment extends Fragment implements CartListener {

    private CounterFab fab;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_goods, container, false);


        GoodsPagerAdapter sectionsPagerAdapter = new GoodsPagerAdapter(getActivity(), getChildFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(v -> openCard());
        fab.setCount(Cart.count());


        return root;
    }

    private void openCard(){
        CartDialog cartDialog = new
                CartDialog();
        cartDialog.setListener((CartListener)this);
        cartDialog.show(getChildFragmentManager(),
                "cart");
    }

    @Override
    public void onClear() {
        Cart.clear();
        fab.setCount(0);
    }

    @Override
    public void onAddRow(String name, float count, float price) {
        Cart.add(name,count,price);
        fab.increase();
    }

    @Override
    public void onPay() {
        float price = Cart.getFinalPrice();
    }
}