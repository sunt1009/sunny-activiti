package com.sunny.activiti.entity;

import lombok.Data;

/**
 * @ClassName: VacationOrderVo
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/4 19:52
 * @Version 1.0
 **/
@Data
public class VacationOrderVo extends VacationOrder {

    private String orderNo;

    private String userName;

    private String flowId;

    private String typeName;
}
