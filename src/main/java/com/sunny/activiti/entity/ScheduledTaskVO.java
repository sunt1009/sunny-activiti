package com.sunny.activiti.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author sunt
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SCHEDULED_TASK")
public class ScheduledTaskVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId("TASK_ID")
    private String taskId;

    /**
     * 类名称
     */
    @TableField("CLASS_NAME")
    private String className;

    /**
     * 方法名称
     */
    @TableField("METHOD_NAME")
    private String methodName;

    /**
     * 请求入参
     */
    @TableField("REQ_PARAMS")
    private String reqParams;

    /**
     * cron表达式
     */
    @TableField("TASK_CRON")
    private String taskCron;

    /**
     * 任务状态（0:启用 1:禁用 ）
     */
    @TableField("TASK_STATE")
    private Integer taskState;

    /**
     * 任务创建时间
     */
    @TableField("CREATE_TIME")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern=DatePattern.NORM_DATETIME_PATTERN,timezone="GMT+8")
    private Date createTime;

    /**
     * 任务修改时间
     */
    @TableField("UPDATE_TIME")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern=DatePattern.NORM_DATETIME_PATTERN,timezone="GMT+8")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;


}
