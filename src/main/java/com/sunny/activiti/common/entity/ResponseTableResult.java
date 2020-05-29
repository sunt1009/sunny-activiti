package com.sunny.activiti.common.entity;

/**
 * @ClassName: ResponseResult
 * @Description:TABLE结果集封装
 * @Author: sunt
 * @Date: 2019/6/515:11
 **/
public class ResponseTableResult<T> {

    public int code; //返回状态码0成功

    private String msg; //返回描述信息

    private long count; //返回总条数

    private T data; //返回内容体

    public ResponseTableResult<T> setCode(ResultCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResponseTableResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseTableResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseTableResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseTableResult<T> setCount(long count) {
        this.count = count;
        return this;
    }

    public long getCount() {
        return count;
    }


}
