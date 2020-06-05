package com.sunny.activiti.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.activiti.common.util.CommonUtil;
import com.sunny.activiti.entity.ProcessLog;
import com.sunny.activiti.entity.User;
import com.sunny.activiti.mapper.ProcessLogMapper;
import com.sunny.activiti.service.ILogService;
import com.sunny.activiti.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: LogServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/4 20:50
 * @Version 1.0
 **/
@Slf4j
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private ProcessLogMapper processLogMapper;
    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public void insertLog(ProcessLog processLog) {
        User currentUser = userService.getCurrentUser();
        processLog.setLogId(CommonUtil.genId());
        processLog.setCreateTime(DateUtil.date());
        processLog.setOperId(currentUser.getUserName());
        processLogMapper.insert(processLog);
    }

    @Override
    public List<ProcessLog> queryOperLog(Long orderNo) {
        QueryWrapper<ProcessLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ORDER_NO",orderNo);
        queryWrapper.orderByDesc("CREATE_TIME");
        return processLogMapper.selectList(queryWrapper);
    }
}
