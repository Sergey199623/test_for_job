package com.sv.test_task.feature.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sv.test_task.R;
import com.sv.test_task.feature.listener.ProductListener;
import com.sv.test_task.feature.model.ProductCodeCountry;
import com.sv.test_task.feature.model.ProductModel;
import com.sv.test_task.feature.model.ProductTestData;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductView> {
    private ArrayList<ProductModel> items;
    public ProductListener listener;

    public ProductAdapter(ProductCodeCountry country) {
        switch (country){
            default:
                items = ProductTestData.getAllProducts();
                break;
            case BY:
                items = ProductTestData.getProductsForBy();
                break;
            case RU:
                items = ProductTestData.getProductsForRu();
                break;
        }
    }


    @NonNull
    @Override
    public ProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductView(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductView holder, int position) {
        ProductModel product = items.get(position);
        holder.setProduct(product);
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }

    public class ProductView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private ImageView pic;
        private View div;
        public ProductView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            pic = itemView.findViewById(R.id.product_pic);
            div = itemView.findViewById(R.id.product_div);

            itemView.setOnClickListener(this);
        }

        public void setProduct(ProductModel product){
            name.setText(product.getName());
            Glide.with(pic.getContext())
                    .load(Uri.parse("file:///android_asset/"+product.getPic()))
                    .into(pic);

            div.setBackgroundResource(product.getCountry().equals(ProductCodeCountry.BY)?R.color.divBy:R.color.divRu);

        }

        @Override
        public void onClick(View v) {
            String name = items.get(getAdapterPosition()).getName();
            int price = items.get(getAdapterPosition()).getPrice();
            if(listener!=null){
                listener.onClick(name,price);
            }
        }
    }
}
