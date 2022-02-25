package com.zgf.study.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zgf.study.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
    }

    private void initView() {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item: " + i);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    private Context context;
    private List<String> list;

    private ItemClickListener listener;

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false);
        Log.e("zgf", "=====onCreateViewHolder==000==");
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Log.e("zgf", "=====onBindViewHolder==111==");
        String s = list.get(position);
        holder.textView.setText(s);

        // TODO 1、设置tag，将当前item的position传进去
        holder.textView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_content);
            // TODO 2、设置点击事件
            // TODO: 这样可以每个item调用一次setOnClickListener()，不会重复调用
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        // TODO 3、获取position
                        int position = (int) v.getTag();
                        // TODO 4、点击回调
                        listener.onItemClickListener(v, position);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClickListener(View view, int position);
    }
}