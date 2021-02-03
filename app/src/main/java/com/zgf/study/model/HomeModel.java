package com.zgf.study.model;

public class HomeModel {
    private String title;
    private String scheme;

    public HomeModel() {}

    public HomeModel(String title, String scheme) {
        this.title = title;
        this.scheme = scheme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
