package com.sunny.activiti;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ProcessTest
 * @Description: 流程演示
 * @Author: sunt
 * @Date: 2020/6/10 10:05
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProcessTest {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * 流程列表查询
     */
    @Test
    public void testQuery() {
        List<Model> list = repositoryService.createModelQuery().list();
        for (Model model : list) {
            log.info("流程名称:{},流程ID:{},部署ID:{}",model.getName(), model.getId(),model.getDeploymentId());
        }
    }

    /**
     * 根据流程ID部署流程
     */
    @Test
    public void deployProce() throws IOException {
        String modelId = "1270539263116574720";
        Model modelData = this.repositoryService.getModel(modelId);
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        byte[] bpmnBytes = null;

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes,"utf-8")).deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
        log.info("流程部署ID:{}",deployment.getId());
    }

    /**
     * 启动流程
     */
    @Test
    public void startProces() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("applyuser","张三");
        String businessKey =  IdUtil.simpleUUID();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ACTIVITI_STUDY",businessKey, variables);
        log.info("部署实例ID:{},流程定义KEY:{},业务ID:{}",processInstance.getProcessInstanceId(),processInstance.getProcessDefinitionKey(), businessKey);
    }

    /**
     * 查询我的代办任务
     */
    @Test
    public void queryMyTask() {
        String assign = "张三";
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
    public void completeTask() {
        //1270545076203814913
        String taskId = "1270545934547156993";
        Map<String, Object> variables = new HashMap<>();
          variables.put("subState","success");
       // variables.put("spState","agree");
        //variables.put("spState","reject");
        taskService.complete(taskId,variables);
        log.info("当前任务办理完成");
    }



}
