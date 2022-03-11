package com.zgf.study.ui.multitype.model;

public class TwoModel extends MultiTypeBaseModel {
    private String title;
    private String content;

    public TwoModel(int type) {
        super(type);
    }

    public TwoModel(int type, String title, String content) {
        super(type);
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
