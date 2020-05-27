package com.sunny.activiti.common.config;

import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import com.sunny.activiti.common.entity.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/27 10:17
 * @Version 1.0
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public <T> ResponseResult<T> bizExceptionHandler( BizException e){
        log.error("业务异常：{}",e.getMsg());
        return ResponseUtil.makeErrRsp(e.getCode(),"业务处理异常:" + e.getMsg());
    }

    /**
     * 处理空指针的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public <T> ResponseResult<T> exceptionHandler(NullPointerException e){
        log.error("空指针异常:{}",e);
        return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,"空指针异常:" + e.getMessage());
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public <T> ResponseResult<T> exceptionHandler(HttpServletRequest req, Exception e){
        log.error("系统异常:{}",e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code,"系统异常:" + e.getMessage());
    }
}
