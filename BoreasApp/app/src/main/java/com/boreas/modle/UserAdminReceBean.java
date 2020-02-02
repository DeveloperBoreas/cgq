package com.boreas.modle;

import java.util.ArrayList;

public class UserAdminReceBean {

    /**
     * data : [{"user_id":1,"user_sex":"男","user_birthday":"1949-10-01","user_clipues":[{"id":1,"clipue_name":"计算机系"},{"id":2,"clipue_name":"物理系"},{"id":3,"clipue_name":"金融系"},{"id":4,"clipue_name":"土木工程系"},{"id":5,"clipue_name":"计算机系"}],"user_jobtitle":null,"user_address":"中国","user_telephone":"01010101010","researchPros":[],"researchPapers":[],"compositions":[],"user_permission":1}]
     * msg :
     * retCode : 0
     */

    private String msg;
    private int retCode;
    private ArrayList<LoginReceBean.DataBean> data;

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

    public ArrayList<LoginReceBean.DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<LoginReceBean.DataBean> data) {
        this.data = data;
    }


}
