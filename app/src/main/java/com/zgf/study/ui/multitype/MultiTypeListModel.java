package com.zgf.study.ui.multitype;

import androidx.annotation.LayoutRes;

import com.zgf.study.ui.multitype.holder.MultiTypeBaseHolder;

class MultiTypeListModel {
    private int type;
    private int resourceId;
    private Class<? extends MultiTypeBaseHolder> aClass;

    public MultiTypeListModel(int type, @LayoutRes int resourceId, Class<? extends MultiTypeBaseHolder> aClass) {
        this.type = type;
        this.resourceId = resourceId;
        this.aClass = aClass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Class<? extends MultiTypeBaseHolder> getaClass() {
        return aClass;
    }

    public void setaClass(Class<? extends MultiTypeBaseHolder> aClass) {
        this.aClass = aClass;
    }
}
