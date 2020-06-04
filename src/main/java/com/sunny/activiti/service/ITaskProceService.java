package com.sunny.activiti.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.entity.TaskVo;

/**
 * @ClassName: ITaskProceService
 * @Description: 流程任务服务
 * @Author: sunt
 * @Date: 2020/6/3 16:14
 * @Version 1.0
 **/
public interface ITaskProceService {

    /**
     * 查询我的代办任务
     * @return
     */
    Page<TaskVo> queryMyTask(PageBean pageBean);

    /**
     *  根据审批单号查询正在执行的流程任务
     * @param vacationId
     * @return
     */
    TaskVo queryTaskById(Long vacationId);

    /**
     * 流程办理
     * @param taskVo
     */
    void completeTask(TaskVo taskVo);
}
