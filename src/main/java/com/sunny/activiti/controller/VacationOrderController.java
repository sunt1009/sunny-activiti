package com.sunny.activiti.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.*;
import com.sunny.activiti.entity.SysDict;
import com.sunny.activiti.entity.VacationOrder;
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

    @RequestMapping("queryList")
    @ResponseBody
    public ResponseTableResult<List<VacationOrder>> queryList(PageBean pageBean) {
        Page<VacationOrder> vacationOrderPage = vacationOrderService.queryVacationOrder(pageBean);
        return ResponseUtil.makeTableRsp(0,vacationOrderPage.getTotal(),vacationOrderPage.getRecords());
    }


    @RequestMapping("toAdd")
    public String toAdd(Model model) {
        List<SysDict> typeList = systemService.querySysDictInfo(SysConstant.VACATION_TYPE);
        model.addAttribute("typeList",typeList);
        return "page/addVacation";
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

}
