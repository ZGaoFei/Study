package com.zgf.study.ui.multitype.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zgf.study.R;
import com.zgf.study.ui.multitype.model.OneModel;

public class MultiTypeOneHolder extends MultiTypeBaseHolder<OneModel> {

    private TextView textView;

    public MultiTypeOneHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.tv_content);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickListener(v, getAdapterPosition());
                }
            }
        });
    }

    @Override
    public void bindViewHolder(OneModel model) {
        textView.setText(model.getContent());
    }

}
