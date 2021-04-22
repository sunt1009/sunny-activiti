package com.sunny.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 流程审批日志表
 * </p>
 *
 * @author sunt
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TL_PROCESS_LOG")
public class ProcessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId("LOG_ID")
    private Long logId;

    /**
     * 审批单ID
     */
    @TableField("ORDER_NO")
    private Long orderNo;

    /**
     * 任务ID
     */
    @TableField("TASK_ID")
    private String taskId;

    /**
     * 任务key
     */
    @TableField("TASK_KEY")
    private String taskKey;

    /**
     * 任务名称
     */
    @TableField("TASK_NAME")
    private String taskName;

    /**
     * 审批状态(对应流程变量值)
     */
    @TableField("APPROV_STATU")
    private String approvStatu;

    /**
     * 操作ID
     */
    @TableField("OPER_ID")
    private String operId;

    /**
     * 操作值
     */
    @TableField("OPER_VALUE")
    private String operValue;

    /**
     * 操作时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;


}
