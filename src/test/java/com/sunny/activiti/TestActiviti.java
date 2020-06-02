package com.sunny.activiti;


import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestJson
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/27 11:20
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestActiviti {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * 查询流程
     */
    @Test
    public void testQuery() {
        List<Model> list = repositoryService.createModelQuery().list();
        for (Model model : list) {
            log.info("流程名称:{},流程ID:{},部署ID:{}",model.getName(),model.getId(),model.getDeploymentId());
        }
    }

    /**
     * 启动流程
     */
    @Test
    public void startProces() {
       // ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("SH-PROCESS", IdUtil.fastSimpleUUID());
        Map<String, Object> variables = new HashMap<>();
        variables.put("user","user-001");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("TEST-FLOW-CALL", IdUtil.simpleUUID(), variables);
        log.info("部署ID:{},流程定义KEY:{}",processInstance.getDeploymentId(),processInstance.getProcessDefinitionKey());
    }

    /**
     * 查询我的代办任务
     */
    @Test
    public void queryMyTask() {
        String assign = "user-001";
        List<Task> list = taskService.createTaskQuery().taskAssignee(assign).list();
        if(IterUtil.isNotEmpty(list)) {
            for (Task task : list) {
                log.info("当前任务ID:{},任务名称:{},当前流程定义key:{}",task.getId(),task.getName(),task.getTaskDefinitionKey());
            }
        }else {
            log.info("未查询到代办人:{}的流程信息",assign);
        }
    }

    /**
     * 办理任务
     */
    @Test
    public void execTask() {
        String taskId = "1266286241683341312";
        taskService.complete(taskId);
        log.info("当前流程办理完成");

    }


}
