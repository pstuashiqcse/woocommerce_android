package com.mcc.eshopper.model;

/**
 * Created by nitul on 4/4/17.
 */

public class ProductModel {
    private String title;
    private String category;
    private int price;
    private int discountPrice;
    private String image;

    public ProductModel (String title, String category, int price, int discountPrice, String image){
        this.title = title;
        this.category = category;
        this.price = price;
        this.discountPrice = discountPrice;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public String getCategory() {
        return category;
    }
}
