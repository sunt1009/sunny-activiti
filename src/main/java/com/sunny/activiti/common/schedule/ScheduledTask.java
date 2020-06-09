package com.sunny.activiti.common.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName: ScheduledTask
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/9 9:45
 * @Version 1.0
 **/
public final class ScheduledTask {

    //被volatile关键字修饰的变量，如果值发生了变更，其他线程立马可见，避免出现脏读的现象
    volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
