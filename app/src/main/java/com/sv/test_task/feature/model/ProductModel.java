package com.sv.test_task.feature.model;

public class ProductModel {
    private String name;
    private String pic;
    private int price;
    private ProductCodeCountry country;

    private ProductModel(String name, String pic, int price) {
        this.name = name;
        this.pic = pic;
        this.price = price;
    }

    public void setCountry(ProductCodeCountry country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public int getPrice() {
        return price;
    }

    public ProductCodeCountry getCountry() {
        return country;
    }

    public static ProductModel genBy(String name, String pic, int price){
        ProductModel productModel = new ProductModel(name,pic,price);
        productModel.setCountry(ProductCodeCountry.BY);
        return productModel;
    }

    public static ProductModel genRu(String name, String pic, int price){
        ProductModel productModel = new ProductModel(name,pic,price);
        productModel.setCountry(ProductCodeCountry.RU);
        return productModel;
    }
}
