package com.zgf.study.ui.mvvm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<DataModel> dataLiveData;

    public DataViewModel() {
        dataLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<DataModel> getData() {
        return dataLiveData;
    }

    public void setName(String name){
        DataModel value = dataLiveData.getValue();
        if (value == null) {
            value = new DataModel();
        }
        value.setName(name);
        dataLiveData.setValue(value);
    }

    public MutableLiveData<DataModel> getLiveData() {
        return dataLiveData;
    }

}
