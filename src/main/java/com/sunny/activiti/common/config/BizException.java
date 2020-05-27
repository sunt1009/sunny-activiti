package com.sunny.activiti.common.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: BizException
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/27 10:17
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class BizException extends RuntimeException {
    /**
     * 状态码
     */
    protected int code;
    /**
     * 返回信息
     */
    protected String msg;

}