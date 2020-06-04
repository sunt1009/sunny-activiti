package com.sunny.activiti.listener;

import com.sunny.activiti.common.util.SpringUtils;
import com.sunny.activiti.entity.FlowMain;
import com.sunny.activiti.service.IFlowInfoService;
import com.sunny.activiti.service.IVacationOrderService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @ClassName: FinishDelegate
 * @Description: 审批完成
 * @Author: sunt
 * @Date: 2020/6/4 17:41
 * @Version 1.0
 **/
@Slf4j
public class FinishDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String flowInstId = execution.getProcessBusinessKey();
        log.info("审批完成更新审批状态:{}",flowInstId);
        IVacationOrderService vacationOrderService = SpringUtils.getBean(IVacationOrderService.class);
        IFlowInfoService flowInfoService = SpringUtils.getBean(IFlowInfoService.class);
        FlowMain flowMain = flowInfoService.queryFlowById(Long.valueOf(flowInstId));
        vacationOrderService.updateState(flowMain.getOrderNo(),3);
        log.info("---------->记录日志...");
    }
}
