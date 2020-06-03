package com.sunny.activiti.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.entity.VacationOrder;

/**
 * @ClassName: IVacationOrderService
 * @Description: 请假服务
 * @Author: sunt
 * @Date: 2020/6/1 17:42
 * @Version 1.0
 **/
public interface IVacationOrderService {

    /**
     * 提交请假申请
     * @param vacationOrder
     */
    void insertVacationOrder(VacationOrder vacationOrder);

    /**
     * 请假单列表查询
     * @param pageBean
     * @return
     */
    Page<VacationOrder> queryVacationOrder(PageBean pageBean);

    /**
     * 根据审批单号查询审批信息
     * @param vacationId
     * @return
     */
    VacationOrder queryVacation(Long vacationId);

    /**
     * 更新审批单状态
     * @param vacationId
     */
    void updateState(Long vacationId,Integer state);
}
