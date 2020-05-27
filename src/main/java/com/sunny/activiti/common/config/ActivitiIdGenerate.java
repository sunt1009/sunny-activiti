package com.sunny.activiti.common.config;

import cn.hutool.core.util.IdUtil;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

/**
 * activiti自定义ID生成策略
 */
@Component
public class ActivitiIdGenerate implements IdGenerator {

    @Override
    public String getNextId() {
        return String.valueOf(IdUtil.getSnowflake(0, 0).nextId());
    }
}
