package com.sunny.activiti.service;

import com.sunny.activiti.entity.ProcessLog;

/**
 * @ClassName: ILogService
 * @Description: 日志服务
 * @Author: sunt
 * @Date: 2020/6/4 20:49
 * @Version 1.0
 **/
public interface ILogService {

    /**
     * 日志记录
     * @param processLog
     */
    void insertLog(ProcessLog processLog);
}
