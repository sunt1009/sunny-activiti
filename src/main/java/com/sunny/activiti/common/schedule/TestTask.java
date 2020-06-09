package com.sunny.activiti.common.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TeSTTask
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/9 9:50
 * @Version 1.0
 **/
@Component("testTask")
@Slf4j
public class TestTask {

    public void taskWithParams(String params) {
        log.info("------->执行带参数任务:{}",params);
    }

    public void taskNoParams() {
        log.info("---------->执行无参数任务");
    }
}
