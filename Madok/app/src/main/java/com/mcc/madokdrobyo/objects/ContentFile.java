package com.mcc.madokdrobyo.objects;

import java.io.Serializable;

/**
 * Created by nitul on 2/1/17.
 */

public class ContentFile implements Serializable{
    private String id;
    private String title;
    private String ytLink;
    private String image;

    public ContentFile( String id, String title, String ytLink, String image){
        this.id = id;
        this.title = title;
        this.ytLink = ytLink;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getYtLink() {
        return ytLink;
    }

    public String getImage() {
        return image;
    }
}
