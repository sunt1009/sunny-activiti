package com.sunny.activiti.service.impl;

import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.activiti.common.schedule.CronTaskRegistrar;
import com.sunny.activiti.common.schedule.SchedulingRunnable;
import com.sunny.activiti.entity.ScheduledTaskVO;
import com.sunny.activiti.mapper.ScheduledTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SysJobRunner
 * @Description: 系统启动加载数据库正常启动的定时任务
 * @Author: sunt
 * @Date: 2020/6/9 9:52
 * @Version 1.0
 **/
@Service
@Slf4j
public class SysJobRunner implements CommandLineRunner {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private ScheduledTaskMapper scheduledTaskMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("------------------>开始加载定时任务...");
        QueryWrapper<ScheduledTaskVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TASK_STATE",0);
        List<ScheduledTaskVO> taskList = scheduledTaskMapper.selectList(queryWrapper);
        if(IterUtil.isNotEmpty(taskList)) {
            for (ScheduledTaskVO scheduledTaskVO : taskList) {
                SchedulingRunnable task = new SchedulingRunnable(scheduledTaskVO.getClassName(), scheduledTaskVO.getMethodName(), scheduledTaskVO.getTaskId());
                cronTaskRegistrar.addCronTask(task, scheduledTaskVO.getTaskCron());
            }
        }

    }
}
