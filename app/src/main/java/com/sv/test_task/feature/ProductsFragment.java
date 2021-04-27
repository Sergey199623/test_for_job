package com.sv.test_task.feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sv.test_task.R;
import com.sv.test_task.feature.adapter.ProductAdapter;
import com.sv.test_task.feature.dialog.CalcDialog;
import com.sv.test_task.feature.listener.CartListener;
import com.sv.test_task.feature.listener.ProductListener;
import com.sv.test_task.feature.model.ProductCodeCountry;

public class ProductsFragment extends Fragment implements ProductListener {

    private static final String ARG = "CODE";
    private ProductCodeCountry code;

    public static ProductsFragment newInstance(ProductCodeCountry code) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG,code);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        code = null;
        if(getArguments()!=null) code = (ProductCodeCountry)getArguments().getSerializable(ARG);
        else if(savedInstanceState!=null) code = (ProductCodeCountry)savedInstanceState.getSerializable(ARG);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_products, container, false);
        RecyclerView list = root.findViewById(R.id.list);

        ProductAdapter adapter = new ProductAdapter(code);
        adapter.listener = this;

        list.setAdapter(adapter);
        return root;
    }



    @Override
    public void onClick(String name, int price) {
        CalcDialog calcDialog = new CalcDialog(name,price);
        calcDialog.setCancelable(false);
        calcDialog.setListener((CartListener) getParentFragment());
        calcDialog.show(getParentFragmentManager(),"calc");
    }
}
