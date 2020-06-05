package com.sunny.activiti.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.*;
import com.sunny.activiti.entity.ProcessLog;
import com.sunny.activiti.entity.SysDict;
import com.sunny.activiti.entity.VacationOrder;
import com.sunny.activiti.entity.VacationOrderVo;
import com.sunny.activiti.service.ILogService;
import com.sunny.activiti.service.ISystemService;
import com.sunny.activiti.service.IVacationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: VacationOrderController
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 17:41
 * @Version 1.0
 **/
@Controller
@RequestMapping("vacation")
public class VacationOrderController {

    @Autowired
    private IVacationOrderService vacationOrderService;
    @Autowired
    private ISystemService systemService;
    @Autowired
    private ILogService logService;

    /**
     * 请假列表查询
     * @param pageBean
     * @return
     */
    @RequestMapping("queryList")
    @ResponseBody
    public ResponseTableResult<List<VacationOrderVo>> queryList(PageBean pageBean) {
        Page<VacationOrderVo> vacationOrderPage = vacationOrderService.queryVacationOrder(pageBean);
        return ResponseUtil.makeTableRsp(0,vacationOrderPage.getTotal(),vacationOrderPage.getRecords());
    }


    @RequestMapping("toAdd")
    public String toAdd(Model model, @RequestParam(value = "orderNo",required = false) String orderNo) {
        List<SysDict> typeList = systemService.querySysDictInfo(SysConstant.VACATION_TYPE);
        model.addAttribute("typeList",typeList);
        if(StrUtil.isNotBlank(orderNo)) {//编辑
            VacationOrder vacationOrder = vacationOrderService.queryVacation(Long.valueOf(orderNo));
            model.addAttribute("vacationOrder",vacationOrder);
            return "page/editVacation";
        }
        return "page/addVacation";
    }

    /**
     * 审批详情页
     * @param model
     * @param flowId
     * @param orderNo
     * @return
     */
    @RequestMapping("provalDetail")
    public String provalDetail(Model model,@RequestParam("flowId") String flowId,@RequestParam("orderNo") String orderNo) {
        List<ProcessLog> logList = logService.queryOperLog(Long.valueOf(orderNo));
        model.addAttribute("logList",logList);
        return "/page/viewFlow";
    }


    /**
     * 填写请假条
     * @param vacationOrder
     * @return
     */
    @PostMapping("saveOrder")
    @ResponseBody
    public ResponseResult<String> saveOrder(@RequestBody VacationOrder vacationOrder) {
        vacationOrderService.insertVacationOrder(vacationOrder);
        return ResponseUtil.makeOKRsp();
    }

    /**
     * 提交请假申请
     * @return
     */
    @PostMapping("submitApply")
    @ResponseBody
    public ResponseResult<String> submitApply(@RequestParam("vacationId") String vacationId) {
        boolean res = vacationOrderService.submitApply(Long.valueOf(vacationId));
        if(res){
            return ResponseUtil.makeOKRsp();
        }else {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,"提交申请失败");
        }
    }

    /**
     * 删除请假条
     * @param vacationId
     * @return
     */
    @PostMapping("delVacation")
    @ResponseBody
    public ResponseResult<String> delVacation(@RequestParam("vacationId") Long vacationId) {
        vacationOrderService.delVacation(vacationId);
        return ResponseUtil.makeOKRsp();
    }

}
