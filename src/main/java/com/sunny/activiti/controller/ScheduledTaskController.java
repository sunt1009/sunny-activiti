package com.sunny.activiti.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.common.entity.ResponseTableResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import com.sunny.activiti.entity.ScheduledTaskVO;
import com.sunny.activiti.service.IScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ScheduledTaskController
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/9 10:00
 * @Version 1.0
 **/
@Controller
@RequestMapping("scheduled")
public class ScheduledTaskController {

    @Autowired
    private IScheduledTaskService scheduledTaskService;

    /**
     * 列表分页查询
     * @param pageBean
     * @return
     */
    @RequestMapping("queryList")
    @ResponseBody
    public ResponseTableResult<List<ScheduledTaskVO>> queryList(PageBean pageBean) {
        Page<ScheduledTaskVO> ScheduledTaskPage = scheduledTaskService.queryList(pageBean);
        return ResponseUtil.makeTableRsp(0,ScheduledTaskPage.getTotal(),ScheduledTaskPage.getRecords());
    }

    /**
     * 新增任务呀
     * @param scheduledTaskVO
     * @return
     */
    @PostMapping("addTask")
    @ResponseBody
    public ResponseResult<String> addTask(@RequestBody ScheduledTaskVO scheduledTaskVO) {
        scheduledTaskService.addTask(scheduledTaskVO);
        return ResponseUtil.makeOKRsp();
    }

    /**
     * 编辑任务
     * @param taskId
     * @return
     */
    @GetMapping("toEdit")
    public String toEdit(@RequestParam("taskId") String taskId, Model model) {
        ScheduledTaskVO taskVO = scheduledTaskService.queryScheduled(taskId);
        model.addAttribute("taskVO",taskVO);
        return "page/editScheduled";
    }

    /**
     * 修改任务
     * @param scheduledTaskVO
     * @return
     */
    @PostMapping("updateTask")
    @ResponseBody
    public ResponseResult<String> updateTask(@RequestBody ScheduledTaskVO scheduledTaskVO) {
        scheduledTaskService.updateTask(scheduledTaskVO);
        return ResponseUtil.makeOKRsp();
    }

    /**
     * 修改状态
     * @param scheduledTaskVO
     * @return
     */
    @PostMapping("updateState")
    @ResponseBody
    public ResponseResult<String> updateState(@RequestBody ScheduledTaskVO scheduledTaskVO) {
        scheduledTaskService.updateState(scheduledTaskVO);
        return ResponseUtil.makeOKRsp();
    }


    /**
     * 删除任务
     * @param taskId
     * @return
     */
    @PostMapping("delTask")
    @ResponseBody
    public ResponseResult<String> delTask(@RequestParam("taskId")String taskId) {
        scheduledTaskService.delTask(taskId);
        return ResponseUtil.makeOKRsp();
    }
}
