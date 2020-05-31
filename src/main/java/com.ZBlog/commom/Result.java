package com.ZBlog.commom;

public class Result<T> {
    //返回错误码
    private Integer code=200;
    //返回信息
    private String message;
    //返回对象
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(){

    }

    public Result(ResultStatus resultStatus, T data) {
        this.data = data;
        this.code = resultStatus.value();
        this.message = resultStatus.getReasonPhrase();
    }
    public void setResult(ResultStatus resultStatus){
        this.code = resultStatus.value();
        this.message = resultStatus.getReasonPhrase();
    }
}
