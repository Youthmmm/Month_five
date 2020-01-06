package com.example.week_test2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week_test2.R;
import com.example.week_test2.entity.ClsEntity;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private Context context;
    private List<ClsEntity> list;

    public LeftAdapter(Context context, List<ClsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LeftAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.left_item_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getCategory().get(0));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftClick.leftItemClick(list.get(position).getCategory().get(0));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    private LeftClick leftClick;

    public void setLeftClick(LeftClick leftClick){
        this.leftClick = leftClick;
    }
    public interface LeftClick{
        void leftItemClick(String id);
    }
}
