package com.sunny.activiti.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.entity.ScheduledTaskVO;

/**
 * @ClassName: IScheduledTaskService
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/9 10:02
 * @Version 1.0
 **/
public interface IScheduledTaskService {
    /**
     * 列表查询
     * @param pageBean
     * @return
     */
    Page<ScheduledTaskVO> queryList(PageBean pageBean);

    /**
     * 新增任务
     * @param scheduledTaskVO
     */
    void addTask(ScheduledTaskVO scheduledTaskVO);

    /**
     * 更新
     * @param scheduledTaskVO
     */
    void updateTask(ScheduledTaskVO scheduledTaskVO);

    /**
     * 删除任务
     * @param taskId
     */
    void delTask(String taskId);

    /**
     * 更改状态
     * @param scheduledTaskVO
     */
    void updateState(ScheduledTaskVO scheduledTaskVO);

    /**
     * 任务查询
     * @param taskId
     * @return
     */
    ScheduledTaskVO queryScheduled(String taskId);
}
