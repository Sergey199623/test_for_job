package com.sv.test_task.feature.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sv.test_task.R;
import com.sv.test_task.feature.Cart;
import com.sv.test_task.feature.model.ProductRow;

import java.text.NumberFormat;
import java.util.ArrayList;

public class RowProductAdapter extends RecyclerView.Adapter<RowProductAdapter.RowView> {
    private ArrayList<ProductRow> items;


    public RowProductAdapter() {
        items = Cart.getItems();
    }


    @NonNull
    @Override
    public RowView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowView(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RowView holder, int position) {
        ProductRow row = items.get(position);
        holder.setRow(row);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class RowView extends RecyclerView.ViewHolder {

        private TextView name, price, info1, info2;

        public RowView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            info1 = itemView.findViewById(R.id.info1);
            info2 = itemView.findViewById(R.id.info2);


        }

        public void setRow(ProductRow row) {

            NumberFormat f = NumberFormat.getNumberInstance();
            f.setMinimumFractionDigits(2);
            f.setMaximumFractionDigits(2);

            name.setText(row.getName());
            price.setText(f.format(row.getPrice()*row.getCount())+" ₽");

            info1.setText("1 КГ x "+ f.format(row.getPrice())+" ₽");
            info2.setText(f.format(row.getCount())+" КГ x "+ f.format(row.getPrice())+" ₽");



        }
    }
}
