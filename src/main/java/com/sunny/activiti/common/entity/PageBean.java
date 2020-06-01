package com.sunny.activiti.common.entity;

import lombok.Data;

/**
 * @ClassName: PageBean
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 16:55
 * @Version 1.0
 **/
@Data
public class PageBean {

    /**
     * 当前页码
     */
    private long page;

    /**
     * 每页显示条数
     */
    private long limit;
}
