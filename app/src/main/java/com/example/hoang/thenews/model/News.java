package com.example.hoang.thenews.model;

/**
 * Created by hoang on 10/22/16.
 */

public class News {

    private String snippet;
    private String imageUrl;
    private String webUrl;

    public News(String snippet, String imageUrl, String webUrl) {
        this.snippet = snippet;
        this.imageUrl = imageUrl;
        this.webUrl = webUrl;
    }

    public News(Doc news) {
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getImageUrlvcl() {
        return imageUrl;
    }
}
