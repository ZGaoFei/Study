package com.zgf.study.ui.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zgf.study.R;

public class DataActivity extends AppCompatActivity {

    private TextView textView;

    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initView();

//        test();
        newAndroidModel();
    }

    private void initView() {
        Button button = findViewById(R.id.bt_set_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setName("hello world!!!");
            }
        });

        textView = findViewById(R.id.tv_name);
    }

    private void test() {
//        viewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DataViewModel.class);
//        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DataViewModel.class);
//        this.viewModel = new DataViewModel();
        viewModel.getLiveData().observe(this, new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                String name = dataModel.getName();
                textView.setText(name);
            }
        });
    }

    private void newAndroidModel() {
        viewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(DataViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                String name = dataModel.getName();
                textView.setText(name);
            }
        });
    }
}