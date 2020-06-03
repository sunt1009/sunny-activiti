package com.sunny.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @ClassName: TaskApprovListener
 * @Description: 任务审批监听
 * @Author: sunt
 * @Date: 2020/6/3 8:54
 * @Version 1.0
 **/
@Slf4j
public class TaskApprovListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("执行审批任务监听器ID:{},办理人:{}",delegateTask.getId(),delegateTask.getAssignee());
        delegateTask.setAssignee("王五");
    }
}
