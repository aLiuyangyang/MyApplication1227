package com.example.dell.myapplication1227.bean;

public class MsgBean {
    private int flag;
    private Object object;

    public MsgBean(int flag, Object object) {
        this.flag = flag;
        this.object = object;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
