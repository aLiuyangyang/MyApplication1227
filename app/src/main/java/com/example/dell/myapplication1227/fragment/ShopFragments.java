package com.example.dell.myapplication1227.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication1227.R;
import com.example.dell.myapplication1227.ShopActivity;
import com.example.dell.myapplication1227.bean.MsgBean;
import com.example.dell.myapplication1227.bean.ShopBean;
import com.example.dell.myapplication1227.presenter.Presenterlmpl;
import com.example.dell.myapplication1227.view.Apis;
import com.example.dell.myapplication1227.view.IView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopFragments extends Fragment implements IView ,View.OnClickListener{

    private Presenterlmpl presenterlmpl;
    private SimpleDraweeView shop_simple;
    private  Button but_name;
    private Button but_price;
    private TextView shop_name;
    private TextView shop_price;
    private ShopBean.DataBean data1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.show,container,false);
        shop_simple=view.findViewById(R.id.shop_simple);
        shop_name=view.findViewById(R.id.shop_name);
        shop_price=view.findViewById(R.id.shop_price);
        but_name=view.findViewById(R.id.but_name);
        but_price=view.findViewById(R.id.but_price);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  ButterKnife.bind(getActivity());
       /* int setname = ((ShopActivity) getActivity()).setname();*/
        presenterlmpl = new Presenterlmpl(this);
        but_name.setOnClickListener(this);
        but_price.setOnClickListener(this);
        Map<String, String> mar = new HashMap<>();
      /*  mar.put("pid",setname+"");*/
        presenterlmpl.setRquestData(Apis.ShopPath, mar, ShopBean.class);
    }

    @Override
    public void setData(Object data) {
        if (data instanceof ShopBean) {
            ShopBean bean = (ShopBean) data;
            data1 = bean.getData();
            shop_name.setText(data1.getTitle());
            shop_price.setText(data1.getPrice()+"");
            String images = bean.getData().getImages();
            String[] split = images.split("\\|");
            Uri uri = Uri.parse(split[0]);
            shop_simple.setImageURI(uri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_price:
                Log.i("aa",data1.getPrice()+"");
                EventBus.getDefault().postSticky(new MsgBean(1,data1.getPrice()));
                break;
            case R.id.but_name:
                Log.i("aa",data1.getTitle()+"");
                EventBus.getDefault().postSticky(new MsgBean(2,data1.getTitle()));

                break;

                default:break;
        }
    }
}
