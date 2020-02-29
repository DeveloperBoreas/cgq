package com.boreas.base;


public class BaseResponse {

    /**
     * msg : success
     * code : 0
     */

    private String msg;
    private int retCode;

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

    @Override
    public String toString() {
        return "BaseResponse{" +
                "msg='" + msg + '\'' +
                ", retCode=" + retCode +
                '}';
    }
}
