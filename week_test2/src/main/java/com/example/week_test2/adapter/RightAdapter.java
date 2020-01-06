package com.example.week_test2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.week_test2.R;
import com.example.week_test2.entity.RightEntity;

import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {
    private Context context;
    private List<RightEntity.DataBean> list;

    public RightAdapter(Context context, List<RightEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RightAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.right_item_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getGoods_name());

        Glide.with(context).load(list.get(position).getGoods_name())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final ImageView iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
