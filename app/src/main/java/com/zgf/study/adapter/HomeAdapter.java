package com.zgf.study.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zgf.androidlib.Utils;
import com.zgf.study.R;
import com.zgf.study.model.HomeModel;
import com.zgf.study.ui.WebViewActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    private Context context;
    private List<HomeModel> list;

    public HomeAdapter(Context context, List<HomeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeHolder holder, int position) {
        final HomeModel homeModel = list.get(position);
        holder.textView.setText(homeModel.getTitle());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scheme = homeModel.getScheme();
                if (!TextUtils.isEmpty(scheme)) {
                    if (scheme.startsWith("http")) {
                        WebViewActivity.start(context, scheme);
                    } else {
                        Utils.skipPageWithScheme(context, homeModel.getScheme());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class HomeHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_title);
        }
    }
}
