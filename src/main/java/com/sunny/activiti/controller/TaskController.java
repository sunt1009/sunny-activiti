package com.sunny.activiti.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.common.entity.ResponseTableResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import com.sunny.activiti.entity.TaskVo;
import com.sunny.activiti.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TaskController
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/3 16:00
 * @Version 1.0
 **/
@RestController
@RequestMapping("task")
public class TaskController {


    @Autowired
    private ITaskService taskService;

    @RequestMapping("queryMyTask")
    public ResponseTableResult<List<TaskVo>> queryMyTask(PageBean pageBean) {
        Page<TaskVo> taskPage = taskService.queryMyTask(pageBean);
        return ResponseUtil.makeTableRsp(0,taskPage.getTotal(),taskPage.getRecords());
    }
}
