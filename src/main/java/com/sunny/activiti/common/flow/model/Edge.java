package com.sunny.activiti.common.flow.model;

import lombok.Data;

/**
 * @ClassName: Edge
 * @Description: 连线
 * @Author: sunt
 * @Date: 2019/8/7 16:23
 * @Version 1.0
 **/
@Data
public class Edge extends GraphElement {
    /**
     * 起点.
     */
    private Node src;

    /**
     * 终点.
     */
    private Node dest;

    /**
     * 循环.
     */
    private boolean cycle;
}
