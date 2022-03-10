package com.zgf.study.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zgf.study.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initView();
    }

    private void initView() {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item: " + i);
        }

        ListView listView = findViewById(R.id.list_view);
        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public ListViewAdapter(@NonNull Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, null);
            viewHolder = new ListViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListViewHolder) convertView.getTag();
        }

        String s = list.get(position);
        viewHolder.textView.setText(s);

        return convertView;
    }
}

class ListViewHolder {
    TextView textView;
}