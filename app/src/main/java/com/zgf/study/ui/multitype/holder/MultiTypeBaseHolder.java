package com.zgf.study.ui.multitype.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zgf.study.ui.multitype.model.MultiTypeBaseModel;

public abstract class MultiTypeBaseHolder<T extends MultiTypeBaseModel> extends RecyclerView.ViewHolder {

    public MultiTypeBaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(T model);
}
