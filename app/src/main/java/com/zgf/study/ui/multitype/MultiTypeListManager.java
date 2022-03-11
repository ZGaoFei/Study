package com.zgf.study.ui.multitype;

import android.util.SparseArray;

import com.zgf.study.R;
import com.zgf.study.ui.multitype.holder.MultiTypeOneHolder;
import com.zgf.study.ui.multitype.holder.MultiTypeTwoHolder;

final class MultiTypeListManager {

    private SparseArray<MultiTypeListModel> listModels;

    public MultiTypeListManager() {
        addModel();
    }

    private void addModel() {
        listModels = new SparseArray<>();
        listModels.put(0, new MultiTypeListModel(0, R.layout.item_type_one_layout, MultiTypeOneHolder.class));
        listModels.put(1, new MultiTypeListModel(1, R.layout.item_type_two_layout, MultiTypeTwoHolder.class));
    }

    public MultiTypeListModel getModel(int type) {
        return listModels.get(type);
    }

}
