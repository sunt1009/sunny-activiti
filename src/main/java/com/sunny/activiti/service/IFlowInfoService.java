package com.sunny.activiti.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.entity.FlowDef;
import com.sunny.activiti.entity.FlowMain;
import com.sunny.activiti.entity.FlowRule;

import java.util.List;

/**
 * @ClassName: IFlowInfoService
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 10:29
 * @Version 1.0
 **/
public interface IFlowInfoService {

    /**
     * 查询流程定义
     * @return
     */
    List<FlowDef> queryFlowDefList();

    /**
     * 查询流程定义
     * @param defId
     * @return
     */
    FlowDef queryFlowDef(Long defId);

    /**
     * 新增流程定义
     * @param flowDef
     */
    void insertFlowDef(FlowDef flowDef);

    /**
     * 新增流程规则
     * @param flowRule
     */
    String insertFlowRule(FlowRule flowRule);

    /**
     * 流程规则查询
     * @return
     */
    Page<FlowRule> queryFlowRule(PageBean pageBean);

    /**
     * 流程匹配服务
     * @param orderId 审批单ID
     */
    void resolve(Long orderId);

    /**
     * 启动流程
     * @param flowMain
     */
    void runFlow(FlowMain flowMain);
}
