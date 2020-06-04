package com.sunny.activiti.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.entity.TaskVo;
import com.sunny.activiti.entity.User;
import com.sunny.activiti.mapper.TaskMapper;
import com.sunny.activiti.service.ITaskProceService;
import com.sunny.activiti.service.IUserService;
import com.sunny.activiti.service.IVacationOrderService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TaskServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/3 16:15
 * @Version 1.0
 **/
@Service
@Slf4j
public class TaskProceServiceImpl implements ITaskProceService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IVacationOrderService vacationOrderService;

    @Override
    public Page<TaskVo> queryMyTask(PageBean pageBean) {
        Page<TaskVo> page = new Page<>(pageBean.getPage(),pageBean.getLimit());
        User currentUser = userService.getCurrentUser();
        return taskMapper.queryMyTask(page,currentUser.getUserId());
    }

    @Override
    public TaskVo queryTaskById(Long vacationId) {
        return taskMapper.queryTaskById(vacationId);
    }

    @Override
    public void completeTask(TaskVo taskVo) {
        Map<String, Object> variables = new HashMap<>();
        if(StrUtil.equals("0",taskVo.getApprovalType())) {//审核通过
            variables.put("spState","agree");
        }else if(StrUtil.equals("1",taskVo.getApprovalType())){//驳回
            vacationOrderService.updateState(Long.valueOf(taskVo.getVacationId()),0);
            variables.put("spState","reject");
        }
        taskService.complete(taskVo.getTaskId(),variables);
    }
}
