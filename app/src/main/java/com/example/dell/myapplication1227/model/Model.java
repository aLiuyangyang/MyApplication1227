package com.example.dell.myapplication1227.model;




import com.example.dell.myapplication1227.callback.MyCallback;

import java.util.Map;

public interface Model {
    void setRquestData(String path, Map<String, String> mar, Class clazz, MyCallback myCallback);
}
