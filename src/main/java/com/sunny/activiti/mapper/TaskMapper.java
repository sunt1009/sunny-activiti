package com.sunny.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.entity.TaskVo;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: TaskMapper
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/3 16:12
 * @Version 1.0
 **/
public interface TaskMapper extends BaseMapper<TaskVo> {

    /**
     * 查询我的代办任务
     * @param userId
     * @return
     */
    Page<TaskVo> queryMyTask(Page<TaskVo> page, @Param("userId") String userId);

    /**
     * 查询审批单当前任务信息
     * @param vacationId
     * @return
     */
    TaskVo queryTaskById(@Param("vacationId") Long vacationId);
}
