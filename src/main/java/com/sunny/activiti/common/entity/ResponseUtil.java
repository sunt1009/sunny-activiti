package com.sunny.activiti.common.entity;

/**
 * @ClassName: ResponseUtil
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/27 10:18
 * @Version 1.0
 **/
public class ResponseUtil {

    private final static String SUCC_MSG = "操作成功";

    public static <T> ResponseResult<T> makeOKRsp() {
        return new ResponseResult<T>().setCode(ResultCode.SUCCESS).setMsg(SUCC_MSG);
    }

    public static <T> ResponseResult<T> makeOKRsp(int code,T data) {
        return new ResponseResult<T>().setCode(code).setMsg(SUCC_MSG).setData(data);
    }

    public static <T> ResponseResult<T> makeOKRsp(T data) {
        return new ResponseResult<T>().setCode(ResultCode.SUCCESS).setMsg(SUCC_MSG).setData(data);
    }

    public static <T> ResponseResult<T> makeErrRsp(int code, String msg) {
        return new ResponseResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> ResponseTableResult<T> makeTableRsp(int code,long count,T data) {
        return new ResponseTableResult<T>().setCode(code).setCount(count).setMsg(SUCC_MSG).setData(data);
    }
}
