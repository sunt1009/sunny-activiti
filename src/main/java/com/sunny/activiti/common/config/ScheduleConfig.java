package com.sunny.activiti.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @ClassName: SchedulingConfig
 * @Description: 添加执行定时任务的线程池配置类
 * @Author: sunt
 * @Date: 2020/6/9 9:43
 * @Version 1.0
 **/
@Configuration
public class ScheduleConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 定时任务执行线程池核心线程数
        taskScheduler.setPoolSize(5);
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
        return taskScheduler;
    }
}
