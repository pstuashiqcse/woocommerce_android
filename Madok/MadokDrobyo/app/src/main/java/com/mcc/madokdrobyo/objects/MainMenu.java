package com.mcc.madokdrobyo.objects;

/**
 * Created by nitul on 1/23/17.
 */

public class MainMenu {

    private String menuId;
    private String name;
    private String status;
    private String image;

    public MainMenu(String menuId, String name, String status, String image){
        this.menuId = menuId;
        this.name = name;
        this.status = status;
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getImage() {
        return image;
    }
}
