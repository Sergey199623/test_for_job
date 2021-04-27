package com.sv.test_task.feature.model;

public class ProductRow {
    private String name;
    private float count, price;

    public ProductRow(String name, float count, float price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }
}
