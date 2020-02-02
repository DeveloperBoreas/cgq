package com.boreas.modle;

import java.util.List;

public class ComBean {
    private String msg;
    private int retCode;
    private List<LoginReceBean.DataBean.Composition> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public List<LoginReceBean.DataBean.Composition> getData() {
        return data;
    }

    public void setData(List<LoginReceBean.DataBean.Composition> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ComBean{" +
                "msg='" + msg + '\'' +
                ", retCode=" + retCode +
                ", data=" + data +
                '}';
    }
}
