package com.sunny.activiti.service;

import org.activiti.engine.runtime.ProcessInstance;

/**
 * @ClassName: IProcesService
 * @Description: 流程服务接口
 * @Author: sunt
 * @Date: 2020/5/28 14:15
 * @Version 1.0
 **/
public interface IProcesService {

    /**
     * 启动流程
     * @param processDefinitionKey 流程定义KEY
     * @param businessKey 业务编码
     * @return
     */
    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey);

}
