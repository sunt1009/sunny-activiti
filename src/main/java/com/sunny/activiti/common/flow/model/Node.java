package com.sunny.activiti.common.flow.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Node
 * @Description: 节点
 * @Author: sunt
 * @Date: 2019/8/7 16:24
 * @Version 1.0
 **/
@Data
public class Node extends GraphElement {
    /**
     * 类型，比如userTask，startEvent.
     */
    private String type;

    /**
     * 是否还未完成.
     */
    private boolean active;

    /**
     * 进入这个节点的所有连线.
     */
    private List<Edge> incomingEdges = new ArrayList<Edge>();

    /**
     * 外出这个节点的所有连线.
     */
    private List<Edge> outgoingEdges = new ArrayList<Edge>();
}
