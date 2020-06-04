package com.sunny.activiti.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.common.util.CommonUtil;
import com.sunny.activiti.entity.FlowMain;
import com.sunny.activiti.entity.User;
import com.sunny.activiti.entity.VacationOrder;
import com.sunny.activiti.mapper.VacationOrderMapper;
import com.sunny.activiti.service.IFlowInfoService;
import com.sunny.activiti.service.IUserService;
import com.sunny.activiti.service.IVacationOrderService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VacationOrderServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 17:43
 * @Version 1.0
 **/
@Service
@Slf4j
public class VacationOrderServiceImpl implements IVacationOrderService {

    @Autowired
    private VacationOrderMapper vacationOrderMapper;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private TaskService taskService;

    @Override
    @Transactional
    public void insertVacationOrder(VacationOrder vacationOrder) {
        vacationOrder.setVacationId(CommonUtil.genId());
        vacationOrder.setVacationState(0);
        User currentUser = userService.getCurrentUser();
        vacationOrder.setUserId(currentUser.getUserId());
        vacationOrder.setCreateTime(DateUtil.date());
        vacationOrder.setSystemCode("1001");
        vacationOrder.setBusiType("2001");
        vacationOrderMapper.insert(vacationOrder);
    }

    @Override
    public Page<VacationOrder> queryVacationOrder(PageBean pageBean) {
        Page<VacationOrder> page = new Page<>(pageBean.getPage(),pageBean.getLimit());
        QueryWrapper<VacationOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("CREATE_TIME");
        Page<VacationOrder> vacationOrderPage = vacationOrderMapper.selectPage(page, queryWrapper);
        List<VacationOrder> records = vacationOrderPage.getRecords();
        for (VacationOrder record : records) {
            record.setOrderNo(String.valueOf(record.getVacationId()));
        }
        return vacationOrderPage;
    }

    @Override
    public VacationOrder queryVacation(Long vacationId) {
        QueryWrapper<VacationOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("VACATION_ID",vacationId);
        return vacationOrderMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public void updateState(Long vacationId, Integer state) {
        VacationOrder vacationOrder = new VacationOrder();
        vacationOrder.setVacationState(state);
        vacationOrder.setVacationId(vacationId);
        vacationOrderMapper.updateById(vacationOrder);
    }

    @Override
    public boolean submitApply(Long vacationId) {
        boolean res = true;
        //匹配流程并指定申请人
        Map<String, Object> variables = new HashMap<>();
        User currentUser = userService.getCurrentUser();
        String flowId = "";
        //匹配流程之前查询是否已经匹配过
        FlowMain flowMain = flowInfoService.queryFlowMainById(vacationId);
        if(ObjectUtil.isNull(flowMain)) {
            variables.put("applyuser",currentUser.getUserId());
            flowId = flowInfoService.resolve(vacationId, variables);
        }else {
            flowId = String.valueOf(flowMain.getFlowId());
        }
        if(StrUtil.isBlank(flowId)) {
            res = false;
            return res;
        }
        //流程流转，对应工作流提交成功
        Task task = flowInfoService.queryTaskByInstId(flowId);
        if(ObjectUtil.isNull(task)) {
            res = false;
            return res;
        }
        variables.put("subState","success");
        log.info("------------->当前办理任务ID:{}",task.getId());
        taskService.complete(task.getId(),variables);
        //更新审批单状态
        this.updateState(vacationId,1);
        return res;
    }

}
