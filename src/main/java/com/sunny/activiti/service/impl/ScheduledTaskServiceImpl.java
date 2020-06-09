package com.sunny.activiti.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.common.schedule.CronTaskRegistrar;
import com.sunny.activiti.common.schedule.SchedulingRunnable;
import com.sunny.activiti.common.util.CommonUtil;
import com.sunny.activiti.entity.ScheduledTaskVO;
import com.sunny.activiti.mapper.ScheduledTaskMapper;
import com.sunny.activiti.service.IScheduledTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: ScheduledTaskServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/9 10:02
 * @Version 1.0
 **/
@Service
@Slf4j
public class ScheduledTaskServiceImpl implements IScheduledTaskService {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private ScheduledTaskMapper scheduledTaskMapper;

    @Override
    public Page<ScheduledTaskVO> queryList(PageBean pageBean) {
        Page<ScheduledTaskVO> page = new Page<>(pageBean.getPage(),pageBean.getLimit());
        return scheduledTaskMapper.selectPage(page,null);
    }

    @Override
    @Transactional
    public void addTask(ScheduledTaskVO scheduledTaskVO) {
        if(StrUtil.isNotBlank(scheduledTaskVO.getTaskId())) {//编辑
            this.updateTask(scheduledTaskVO);
        }else { //新增
            scheduledTaskVO.setTaskId(String.valueOf(CommonUtil.genId()));
            scheduledTaskVO.setCreateTime(DateUtil.date());
            scheduledTaskMapper.insert(scheduledTaskVO);
            if(scheduledTaskVO.getTaskState() == 0) {
                log.info("----------->添加任务到线程....");
                SchedulingRunnable task = new SchedulingRunnable(scheduledTaskVO.getClassName(), scheduledTaskVO.getMethodName(), scheduledTaskVO.getReqParams());
                cronTaskRegistrar.addCronTask(task, scheduledTaskVO.getTaskCron());
            }
        }

    }

    @Override
    @Transactional
    public void updateTask(ScheduledTaskVO scheduledTaskVO) {
        QueryWrapper<ScheduledTaskVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TASK_ID",scheduledTaskVO.getTaskId());
        scheduledTaskMapper.updateById(scheduledTaskVO);
        //移除任务
        SchedulingRunnable task = new SchedulingRunnable(scheduledTaskVO.getClassName(), scheduledTaskVO.getMethodName(), scheduledTaskVO.getReqParams());
        cronTaskRegistrar.removeCronTask(task);
        if(scheduledTaskVO.getTaskState() == 0) {
            //改为启用状态重新添加任务
            cronTaskRegistrar.addCronTask(task, scheduledTaskVO.getTaskCron());
        }
    }

    @Override
    @Transactional
    public void delTask(String taskId) {
        ScheduledTaskVO scheduledTaskVO = this.queryScheduled(taskId);
        //移除任务
        SchedulingRunnable task = new SchedulingRunnable(scheduledTaskVO.getClassName(), scheduledTaskVO.getMethodName(), scheduledTaskVO.getReqParams());
        cronTaskRegistrar.removeCronTask(task);

        QueryWrapper<ScheduledTaskVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TASK_ID",taskId);
        scheduledTaskMapper.delete(queryWrapper);
    }

    @Override
    public void updateState(ScheduledTaskVO scheduledTaskVO) {
        ScheduledTaskVO bean = this.queryScheduled(scheduledTaskVO.getTaskId());

        bean.setTaskState(scheduledTaskVO.getTaskState());
        bean.setUpdateTime(DateUtil.date());
        scheduledTaskMapper.updateById(bean);
        //移除任务
        SchedulingRunnable task = new SchedulingRunnable(bean.getClassName(), bean.getMethodName(), bean.getReqParams());
        cronTaskRegistrar.removeCronTask(task);
        if(bean.getTaskState() == 0) {
            //改为启用状态重新添加任务
            cronTaskRegistrar.addCronTask(task, bean.getTaskCron());
        }
    }

    @Override
    public ScheduledTaskVO queryScheduled(String taskId) {
        QueryWrapper<ScheduledTaskVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TASK_ID",taskId);
        ScheduledTaskVO bean = scheduledTaskMapper.selectOne(queryWrapper);
        return bean;
    }
}
