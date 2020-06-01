package com.sunny.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.common.util.CommonUtil;
import com.sunny.activiti.entity.FlowDef;
import com.sunny.activiti.entity.FlowRule;
import com.sunny.activiti.mapper.FlowDefMapper;
import com.sunny.activiti.mapper.FlowRuleMapper;
import com.sunny.activiti.service.IFlowInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FlowInfoServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 10:30
 * @Version 1.0
 **/
@Service
@Slf4j
public class FlowInfoServiceImpl implements IFlowInfoService {

    @Autowired
    private FlowDefMapper flowDefMapper;
    @Autowired
    private FlowRuleMapper flowRuleMapper;

    @Override
    public List<FlowDef> queryFlowDefList() {
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FLOW_STATE",0);
        return flowDefMapper.selectList(queryWrapper);
    }

    @Transactional
    @Override
    public void insertFlowDef(FlowDef flowDef) {
        String flowCode = flowDef.getFlowCode();
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FLOW_CODE",flowCode);
        Integer count = flowDefMapper.selectCount(queryWrapper);
        if(count <= 0) {
            flowDef.setDefId(CommonUtil.genId());
            flowDef.setFlowState(0);
            flowDefMapper.insert(flowDef);
        }

    }

    @Override
    public String insertFlowRule(FlowRule flowRule) {
        String resMsg = "";
        QueryWrapper<FlowRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DEF_ID",flowRule.getDefId());
        Integer count = flowRuleMapper.selectCount(queryWrapper);
        if(count <= 0) {
            flowRule.setRuleId(CommonUtil.genId());
            flowRule.setSystemCode(String.join(",",flowRule.getSystemIds()));
            flowRule.setBusiType(String.join(",",flowRule.getBusiTypes()));
            flowRuleMapper.insert(flowRule);
            return resMsg;
        }else {
            resMsg = "您选择的【"+flowRule.getDefName()+"】已经配置,请重新选择业务流程";
            return resMsg;
        }
    }

    @Override
    public Page<FlowRule> queryFlowRule(PageBean pageBean) {
        Page<FlowRule> page = new Page<>(pageBean.getPage(),pageBean.getLimit());
        Page<FlowRule> flowRulePage = flowRuleMapper.selectPage(page, null);
        return flowRulePage;
    }
}
