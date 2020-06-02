package com.sunny.activiti.common.flow.model;

import lombok.Data;

/**
 * @ClassName: GraphElement
 * @Description: 节点和连线的父类
 * @Author: sunt
 * @Date: 2019/8/7 16:25
 * @Version 1.0
 **/
@Data
public class GraphElement {
    /**
     * 实例id，历史的id.
     */
    private String id;

    /**
     * 节点名称，bpmn图形中的id.
     */
    private String name;
}
