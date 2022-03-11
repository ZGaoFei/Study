package com.zgf.study.ui.multitype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zgf.study.ui.multitype.holder.MultiTypeBaseHolder;
import com.zgf.study.ui.multitype.model.MultiTypeBaseModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeBaseHolder> {

    private Context context;
    private List<? extends MultiTypeBaseModel> list;

    private MultiTypeListManager manager;

    public MultiTypeAdapter(Context context, List<? extends MultiTypeBaseModel> list) {
        this.context = context;
        this.list = list;

        manager = new MultiTypeListManager();
    }

    @NonNull
    @Override
    public MultiTypeBaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MultiTypeListModel model = manager.getModel(viewType);
        if (model == null || model.getResourceId() == 0 || model.getaClass() == null) {
            return null;
        }
        View view = LayoutInflater.from(context).inflate(model.getResourceId(), parent, false);
        Class<? extends MultiTypeBaseHolder> aClass = model.getaClass();
        MultiTypeBaseHolder holder = null;
        try {
            Constructor<? extends MultiTypeBaseHolder> constructor = aClass.getConstructor(View.class);
            holder = constructor.newInstance(view);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MultiTypeBaseHolder holder, int position) {
        holder.bindViewHolder(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (list == null || list.size() == 0) {
            return super.getItemViewType(position);
        }
        return list.get(position).getType();
    }
}
