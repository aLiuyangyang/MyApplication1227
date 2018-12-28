package com.example.dell.myapplication1227.adaptr;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.myapplication1227.fragment.CcommFragments;
import com.example.dell.myapplication1227.fragment.PertFragments;
import com.example.dell.myapplication1227.fragment.ShopFragments;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    String[] name=new String[]{"首页","详情","评论"};
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new ShopFragments();
            case 1:
                return new PertFragments();

            case 2:
                return new CcommFragments();

            default:
                return new Fragment();
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }

    @Override
    public int getCount() {
        return name.length;
    }
}
