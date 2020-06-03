package com.sunny.activiti.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.entity.TaskVo;
import com.sunny.activiti.entity.User;
import com.sunny.activiti.mapper.TaskMapper;
import com.sunny.activiti.service.ITaskService;
import com.sunny.activiti.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TaskServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/3 16:15
 * @Version 1.0
 **/
@Service
@Slf4j
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private IUserService userService;

    @Override
    public Page<TaskVo> queryMyTask(PageBean pageBean) {
        Page<TaskVo> page = new Page<>(pageBean.getPage(),pageBean.getLimit());
        User currentUser = userService.getCurrentUser();
        return taskMapper.queryMyTask(page,currentUser.getUserName());
    }
}
