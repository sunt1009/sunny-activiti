package com.sunny.activiti.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.entity.TaskVo;

/**
 * @ClassName: ITaskService
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/3 16:14
 * @Version 1.0
 **/
public interface ITaskService {

    /**
     * 查询我的代办任务
     * @return
     */
    Page<TaskVo> queryMyTask(PageBean pageBean);
}
