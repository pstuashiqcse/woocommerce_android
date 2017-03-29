package com.mcc.eshopper.model;

/**
 * Created by Ashiq on 3/29/2017.
 */
public class CategoryModel {

    private int id;
    private String name;
    private String image;

    public CategoryModel(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
