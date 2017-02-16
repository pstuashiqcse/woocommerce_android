package com.mcc.madokdrobyo.objects;

import java.io.Serializable;

/**
 * Created by nitul on 1/25/17.
 */

public class Content implements Serializable{
    private String contentId;
    private String title;
    private String details;

    public Content( String contentId, String title, String details){
        this.contentId = contentId;
        this.title = title;
        this.details = details;
    }

    public String getContentId() {
        return contentId;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }
}
