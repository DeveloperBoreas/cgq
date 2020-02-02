package com.example.boreas.response;

public class Response<T> {

    private T data;
    private int success;
    private String msg;
    private int retCode;

    public Response(int success) {
        this.success = success;
    }

    public Response(int success, T data) {
        this.success = success;
        this.data = data;
    }

    public T getdata() {
        return this.data;
    }

    public int isSuccess() {
        return this.success;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getretCode() {
        return this.retCode;
    }

    public Response setretCode(int retCode) {
        this.retCode = retCode;
        return this;
    }

    public static Response ok() {
        return builder().success(1).build();
    }

    public static <T> Response ok(T data) {
        System.out.print(builder().toString());
        return builder().success(1).data(data).build();
    }

    public static <T> Response ok(T data, int retCode) {
        return builder().success(1).data(data).retCode(retCode).build();
    }

    public static Response fail() {
        return builder().success(0).build();
    }

    public static Response fail(String msg) {
        return builder().success(0).msg(msg).build();
    }

    public static Response fail(int retCode) {
        return builder().success(0).retCode(retCode).build();
    }

    public static Response fail(int retCode, String msg) {
        return builder().success(0).msg(msg).retCode(retCode).build();
    }

    private static int $default$retCode() {
        return -1;
    }


    public static <T> Response.ResponseBuilder<T> builder() {
        return new Response.ResponseBuilder();
    }

    //    @ConstructorProperties({"data", "success", "msg", "retCode"})
    public Response(T data, int success, String msg, int retCode) {
        this.data = data;
        this.success = success;
        this.msg = msg;
        this.retCode = retCode;
    }

    public static class ResponseBuilder<T> {
        private T data;
        private int success;
        private String msg;
        private boolean retCode$set;
        private int retCode;
        private Response response;

        ResponseBuilder() {
        }

        public Response.ResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Response.ResponseBuilder<T> success(int success) {
            this.success = success;
            return this;
        }

        public Response.ResponseBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Response.ResponseBuilder<T> retCode(int retCode) {
            this.retCode = retCode;
            this.retCode$set = true;
            return this;
        }


        public Response<T> build() {

            response = new Response(this.data, this.success, this.msg == null ? "" : this.msg, this.retCode$set ? this.retCode : Response.$default$retCode());
            // String json = JsonKit.toString(aRestResponse);
            //System.out.print(json+"\n");
            return response;
        }

        public String toString() {
            return "";
            //return "RestResponse.RestResponseBuilder(data=" + this.data + ", success=" + this.success + ", msg=" + this.msg + ", retCode=" + this.retCode +")";

        }
    }
}
