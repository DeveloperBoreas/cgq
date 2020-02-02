package com.boreas.modle;

import java.util.List;

public class ClipuesBean {

    /**
     * data : [{"clipue_name":"计算机系","id":1},{"clipue_name":"物理系","id":2},{"clipue_name":"金融系","id":3},{"clipue_name":"土木工程系","id":4},{"clipue_name":"计算机系","id":5}]
     * msg :
     * retCode : 0
     */

    private String msg;
    private int retCode;
    private List<LoginReceBean.DataBean.Clipue> data;

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

    public List<LoginReceBean.DataBean.Clipue> getData() {
        return data;
    }

    public void setData(List<LoginReceBean.DataBean.Clipue> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClipuesBean{" +
                "msg='" + msg + '\'' +
                ", retCode=" + retCode +
                ", data=" + data +
                '}';
    }
}
