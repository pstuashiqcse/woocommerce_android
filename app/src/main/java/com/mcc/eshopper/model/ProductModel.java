package com.mcc.eshopper.model;

import java.util.ArrayList;

/**
 * Created by nitul on 4/4/17.
 */

public class ProductModel {
    private String title;
    private ArrayList <String> category;
    private String price;
    private String salePrice;
    private String regularPrice;
    private String image;

    public ProductModel (String title, ArrayList <String> category, String price, String regularPrice, String salePrice, String image){
        this.title = title;
        this.category = category;
        this.price = price;
        this.salePrice = salePrice;
        this.regularPrice = regularPrice;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList <String> getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getImage() {
        return image;
    }


}
