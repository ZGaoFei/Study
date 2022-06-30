package com.zgf.study.ui.multitype;

import android.view.View;

public interface ItemClickListener {

    void onClickListener(View view, int position);

    void onLongClickListener(View view, int position);
}
