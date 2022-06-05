package com.zgf.study.presenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LifecycleRegistryOwner;

public class OnePresenter implements LifecycleRegistryOwner {
    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return null;
    }
}
