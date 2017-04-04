package com.mcc.eshopper.model;

/**
 * Created by Nasir on 3/29/17.
 */
public class ProductListModel {

    private int id;
    private String name;
    private String image;

    public ProductListModel(int id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
