package com.zgf.study.ui.multitype.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zgf.study.R;
import com.zgf.study.ui.multitype.model.TwoModel;

public class MultiTypeTwoHolder extends MultiTypeBaseHolder<TwoModel> {

    private TextView tvTitle;
    private TextView tvContent;

    public MultiTypeTwoHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvContent = itemView.findViewById(R.id.tv_content);
    }

    @Override
    public void bindViewHolder(TwoModel model) {
        tvTitle.setText(model.getTitle());
        tvContent.setText(model.getContent());
    }
}
