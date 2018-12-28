package com.example.dell.myapplication1227;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.myapplication1227.adaptr.MyFragmentAdapter;

public class ShopActivity extends AppCompatActivity{

    private TabLayout tab;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        tab.setupWithViewPager(pager);
        pager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));


    }
      public int setname(){
        Intent intent1=getIntent();
          int pid = intent1.getIntExtra("pid", 1);
          return pid;
      }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        pager = (ViewPager) findViewById(R.id.pager);
    }
}
