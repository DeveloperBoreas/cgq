package com.boreas.modle;

import java.util.List;

public class ProBean {

    /**
     * data : [{"id":1,"pro_current_status":"进行中","pro_finish_date":"2020-01-31","pro_level":1,"pro_money":"500000","pro_name":"学校网站重构"}]
     * msg :
     * retCode : 0
     */

    private String msg;
    private int retCode;
    private List<LoginReceBean.DataBean.ResearchPro> data;

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

    public List<LoginReceBean.DataBean.ResearchPro> getData() {
        return data;
    }

    public void setData(List<LoginReceBean.DataBean.ResearchPro> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProBean{" +
                "msg='" + msg + '\'' +
                ", retCode=" + retCode +
                ", data=" + data +
                '}';
    }
}
