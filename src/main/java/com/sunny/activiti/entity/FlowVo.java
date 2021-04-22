package com.sunny.activiti.entity;

import lombok.Data;

/**
 * @ClassName: FlowVo
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 10:19
 * @Version 1.0
 **/
@Data
public class FlowVo {
    /**
     * 部署ID
     */
    private String deployMentId;

    /**
     * 流程名称
     */
    private String flowName;

    /**
     * 流程key
     */
    private String flowKey;
}
