package com.example.dell.myapplication1227.adaptr;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.myapplication1227.R;
import com.example.dell.myapplication1227.bean.Bean;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyXecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

      private List<Bean.DataBean> list;
      private Context context;

    public MyXecyAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<Bean.DataBean> lists) {
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }
    public void addList(List<Bean.DataBean> lists) {
        list.addAll(lists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_main,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
          ViewHolder holder= (ViewHolder) viewHolder;
          holder.item_name.setText(list.get(i).getTitle());
          holder.item_price.setText(list.get(i).getPrice()+"");
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simpleDraweeView.setImageURI(uri);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

           itemCilck.click(list.get(i).getPid());
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_simple)
       SimpleDraweeView simpleDraweeView;
        @BindView(R.id.item_name)
        TextView item_name;
        @BindView(R.id.item_price)
        TextView item_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private ItemCilck itemCilck;

    public void setItemCilck(ItemCilck itemCilck) {
        this.itemCilck = itemCilck;
    }

    public interface ItemCilck{
        void click(int pid);
    }
}
