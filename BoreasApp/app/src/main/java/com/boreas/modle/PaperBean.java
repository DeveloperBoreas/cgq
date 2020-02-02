package com.boreas.modle;

import java.util.List;

public class PaperBean {
    private String msg;
    private int retCode;
    private List<LoginReceBean.DataBean.ResearchPaper> data;

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

    public List<LoginReceBean.DataBean.ResearchPaper> getData() {
        return data;
    }

    public void setData(List<LoginReceBean.DataBean.ResearchPaper> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PaperBean{" +
                "msg='" + msg + '\'' +
                ", retCode=" + retCode +
                ", data=" + data +
                '}';
    }
}
