package com.example.dell.myapplication1227.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication1227.R;
import com.example.dell.myapplication1227.bean.MsgBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CcommFragments extends Fragment {
    @BindView(R.id.text_com_name)
    TextView text_com_name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.comment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void message(MsgBean msg){
      if (msg.getFlag()==2){
          Log.i("aa",msg.getObject().toString());
          text_com_name.setText(msg.getObject().toString());
      }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
