package com.meteoru.kalei.customtoast;

public class Article {
    private String title;
    private String url;
    private String imageUrl;

    public Article(String title, String url, String imageUrl) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public Article() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String string){
        this.imageUrl = imageUrl;
    }

}
