package com.sunny.activiti.service.impl;

import com.sunny.activiti.service.IProcesService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ProcesServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/28 14:55
 * @Version 1.0
 **/@Service
@Slf4j
public class ProcesServiceImpl implements IProcesService {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey,businessKey);
    }
}
