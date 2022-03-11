package com.zgf.study.ui.multitype.model;

public class OneModel extends MultiTypeBaseModel {
    private String content;

    public OneModel(int type) {
        super(type);
    }

    public OneModel(int type, String content) {
        this(type);

        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
