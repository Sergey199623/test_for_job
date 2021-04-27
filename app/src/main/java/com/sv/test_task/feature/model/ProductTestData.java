package com.sv.test_task.feature.model;

import java.util.ArrayList;
import java.util.Collections;

public class ProductTestData {
    // Отвечает за наполнение тестовыми данными

    public static ArrayList<ProductModel> getProductsForBy(){
        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(ProductModel.genBy("Картофель","by1.jpg",60));
        products.add(ProductModel.genBy("Лук","by2.jpg",20));
        products.add(ProductModel.genBy("Чеснок","by3.jpg",10));
        products.add(ProductModel.genBy("Огурцы","by4.jpg",100));
       // Collections.sort(products, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        return products;
    }

    public static ArrayList<ProductModel> getProductsForRu(){
        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(ProductModel.genRu("Петрушка","ru1.jpg",25));
        products.add(ProductModel.genRu("Морковь","ru2.jpg",50));
        products.add(ProductModel.genRu("Свекла","ru3.jpg",100));
        products.add(ProductModel.genRu("Укроп","ru4.jpg",25));
        Collections.sort(products, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        return products;
    }

    public static ArrayList<ProductModel> getAllProducts(){
        ArrayList<ProductModel> products = new ArrayList<>();
        products.addAll(getProductsForBy());
        products.addAll(getProductsForRu());
        Collections.sort(products, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        return products;
    }
}
