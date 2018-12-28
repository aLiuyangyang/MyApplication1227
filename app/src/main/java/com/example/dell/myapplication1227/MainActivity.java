package com.example.dell.myapplication1227;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dell.myapplication1227.adaptr.MyXecyAdapter;
import com.example.dell.myapplication1227.bean.Bean;
import com.example.dell.myapplication1227.presenter.Presenterlmpl;
import com.example.dell.myapplication1227.view.Apis;
import com.example.dell.myapplication1227.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener{
     private Presenterlmpl presenterlmpl;
     @BindView(R.id.main_xrcy)
      XRecyclerView xRecyclerView;
     @BindView(R.id.main_gxrcy)
     XRecyclerView gRecyclerView;
     @BindView(R.id.edit)
     EditText editText;
     @BindView(R.id.main_source)
     ImageView imageView;
     private  int i=0;
     private MyXecyAdapter adapter;
     private int page;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_des).setOnClickListener(this);
        ButterKnife.bind(this);
        imageView.setOnClickListener(this);

        presenterlmpl=new Presenterlmpl(this);
        adapter=new MyXecyAdapter(this);

        page=1;
        name = editText.getText().toString();

        //线性
        linear();
        //网格
        grid();
        adapter.setItemCilck(new MyXecyAdapter.ItemCilck() {
            @Override
            public void click(int pid) {
                Intent intent1=new Intent(MainActivity.this,ShopActivity.class);
                intent1.putExtra("pid",pid);
                startActivity(intent1);
            }
        });
    }

    private void grid() {
        GridLayoutManager linearLayoutManager=new GridLayoutManager(this,2);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        gRecyclerView.setLayoutManager(linearLayoutManager);
        gRecyclerView.setAdapter(adapter);
        gRecyclerView.setPullRefreshEnabled(true);
        gRecyclerView.setLoadingMoreEnabled(true);
        gRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                load(name,page);
                gRecyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                load(name,page);
                gRecyclerView.loadMoreComplete();
            }
        });
    }

    private void linear() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                load(name,page);
                xRecyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                load(name,page);
                xRecyclerView.loadMoreComplete();
            }
        });

    }


    private void load(String name,int page) {
        Map<String,String> mar=new HashMap<>();

        mar.put("keywords",name);
        mar.put("page",page+"");
        presenterlmpl.setRquestData(Apis.BeanPath,mar,Bean.class);
    }
    @Override
    public void onClick(View v) {
        page=1;
      switch (v.getId()){
          case R.id.main_des:
                if (i%2==0){
                  xRecyclerView.setVisibility(View.VISIBLE);
                  gRecyclerView.setVisibility(View.GONE);
                }else {
                    xRecyclerView.setVisibility(View.GONE);
                    gRecyclerView.setVisibility(View.VISIBLE);
                }
                i++;

              break;
          case R.id.main_source:
              name = editText.getText().toString();
              load(name,page);
              break;
              default:break;
      }
    }

    @Override
    public void setData(Object data) {
       if (data instanceof Bean){
           Bean bean= (Bean) data;
           if (page==1){
               adapter.setList(bean.getData());
           }else {
               adapter.addList(bean.getData());
           }
           page++;

       }
    }


}
