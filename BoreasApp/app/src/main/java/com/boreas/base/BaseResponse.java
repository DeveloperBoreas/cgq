package com.boreas.base;

/**
 * author : 王秀清
 * e-mail :  13051089921@163.com
 * create :   On 2018/8/27下午9:14
 * <p>
 * des    :
 */
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
