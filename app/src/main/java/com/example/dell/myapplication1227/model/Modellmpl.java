package com.example.dell.myapplication1227.model;


import com.example.dell.myapplication1227.callback.MyCallback;
import com.example.dell.myapplication1227.util.ICallback;
import com.example.dell.myapplication1227.util.Okhttputils;
import com.example.dell.myapplication1227.util.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

public class Modellmpl implements Model{

    @Override
    public void setRquestData(String path, final Map<String, String> mar, final Class clazz, final MyCallback myCallback) {
        /*Okhttputils.getmInstance().postEnQueue(path, mar, clazz, new ICallback() {
            @Override
            public void setSuccess(Object obj) {
                myCallback.setData(obj);
            }

            @Override
            public void setfiee(Exception ex) {
                 myCallback.setData(ex);
            }
        });*/
        RetrofitManager.getInstance().post(path,mar).setRetrofitListener(new RetrofitManager.RetrofitListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                myCallback.setData(o);
            }

            @Override
            public void onFail(String ex) {
               myCallback.setData(ex);
            }
        });
    }
}
