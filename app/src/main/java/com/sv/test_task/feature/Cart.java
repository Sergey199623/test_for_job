package com.sv.test_task.feature;

import com.sv.test_task.feature.model.ProductRow;

import java.util.ArrayList;

public class Cart {

    private static Cart sSoleInstance;
    private ArrayList<ProductRow> items;


    private Cart() {
        items = new ArrayList<>();
    }

    private static Cart getInstance() {
        if (sSoleInstance == null) sSoleInstance = new Cart();
        return sSoleInstance;
    }


    public static float getFinalPrice(){
        float price = 0f;
        for(ProductRow item:getItems()){
            price+=item.getPrice()*item.getCount();
        }
        return price;
    }

    public static ArrayList<ProductRow> getItems() {
        return getInstance().items;
    }

    public static void clear() {
        sSoleInstance = null;
    }

    public static int count() {
        return getInstance().items.size();
    }

    public static void add(String name, float count, float price) {
        getInstance().items.add(new ProductRow(name, count, price));
    }


}
