package com.example.dell.myapplication1227.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager {
    private String MY_URL = "http://www.zhaoapi.cn/product/";
    private static RetrofitManager mRetrofitManager;
    private final MyApis myApis;

    //单例
    public static synchronized RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    public RetrofitManager() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(MY_URL)
                .client(client)
                .build();
        myApis = retrofit.create(MyApis.class);
    }

    //post请求
    public RetrofitManager post(String path, Map<String, String> mar) {
        if (mar == null) {
            mar = new HashMap<>();
        }
        myApis.post(path, mar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return mRetrofitManager;
    }

    private Observer observer = new Observer<ResponseBody>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
              if (retrofitListener!=null){
                  retrofitListener.onFail(e.getMessage());
              }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data = responseBody.string();
                if (retrofitListener!=null){
                    retrofitListener.onSuccess(data);
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    };
    private RetrofitListener retrofitListener;

    public void setRetrofitListener(RetrofitListener retrofitListener) {
        this.retrofitListener = retrofitListener;
    }

    public interface RetrofitListener{
        void onSuccess (String data);
        void onFail(String ex);
    }
}