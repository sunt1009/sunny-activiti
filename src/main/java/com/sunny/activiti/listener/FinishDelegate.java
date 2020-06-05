package com.sunny.activiti.listener;

import com.sunny.activiti.common.entity.SysConstant;
import com.sunny.activiti.common.util.SpringUtils;
import com.sunny.activiti.entity.FlowMain;
import com.sunny.activiti.entity.ProcessLog;
import com.sunny.activiti.entity.User;
import com.sunny.activiti.service.IFlowInfoService;
import com.sunny.activiti.service.ILogService;
import com.sunny.activiti.service.IUserService;
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
        vacationOrderService.updateState(flowMain.getOrderNo(), SysConstant.COMPLETED_STATE);
        //记录日志
        ILogService logService = SpringUtils.getBean(ILogService.class);
        IUserService userService = SpringUtils.getBean(IUserService.class);
        ProcessLog bean = new ProcessLog();
        User user = userService.getCurrentUser();
        User userInfo = userService.queryUserById(user.getUserId());
        bean.setOrderNo(flowMain.getOrderNo());
        bean.setTaskName("审批完成");
        bean.setTaskKey("finish_end");
        bean.setApprovStatu("finish_end");
        bean.setOperValue("审批完工");
        logService.insertLog(bean);
    }
}
