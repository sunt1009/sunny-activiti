package com.sunny.activiti.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.*;
import com.sunny.activiti.entity.FlowDef;
import com.sunny.activiti.entity.FlowRule;
import com.sunny.activiti.entity.SysDict;
import com.sunny.activiti.service.IFlowInfoService;
import com.sunny.activiti.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: FlowDefController
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 10:14
 * @Version 1.0
 **/
@Controller
@RequestMapping("flow")
public class FlowDefController {

    @Autowired
    private ISystemService systemService;
    @Autowired
    private IFlowInfoService flowInfoService;

    /**
     * 查询流程规则
     * @return
     */
    @RequestMapping("queryFlowRule")
    @ResponseBody
    public ResponseTableResult<List<FlowRule>> queryFlowRule(PageBean pageBean) {
        Page<FlowRule> flowRulePage = flowInfoService.queryFlowRule(pageBean);
        return ResponseUtil.makeTableRsp(0,flowRulePage.getTotal(),flowRulePage.getRecords());
    }

    /**
     * 跳转到新增规则页面
     * @param model
     * @return
     */
    @RequestMapping("addFlowRule")
    public String addFlowRule(org.springframework.ui.Model model) {
        List<SysDict> systemList = systemService.querySysDictInfo(SysConstant.SYSTEM_CODE);
        List<SysDict> busitypeList = systemService.querySysDictInfo(SysConstant.BUSI_TYPE);
        List<FlowDef> flowDefList = flowInfoService.queryFlowDefList();
        model.addAttribute("systemList",systemList);
        model.addAttribute("busitypeList",busitypeList);
        model.addAttribute("flowDefList",flowDefList);
        return "page/addFlowRule";
    }


    @PostMapping("submitFlowRule")
    @ResponseBody
    public ResponseResult<String> submitFlowRule(@RequestBody FlowRule flowRule) {
        String resMsg = flowInfoService.insertFlowRule(flowRule);
        if(StrUtil.isNotBlank(resMsg)) {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,resMsg);
        }
        return ResponseUtil.makeOKRsp(resMsg);
    }
}
