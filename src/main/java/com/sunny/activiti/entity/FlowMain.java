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
 * 流程与审批单表关联表
 * </p>
 *
 * @author sunt
 * @since 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TF_FLOW_MAIN")
public class FlowMain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("FLOW_INST_ID")
    private Long flowInstId;

    /**
     * 审批单号
     */
    @TableField("ORDER_NO")
    private Long orderNo;

    /**
     * 流程定义编码(创建流程时设置的)
     */
    @TableField("FLOW_DEF_ID")
    private String flowDefId;

    /**
     * 流程ID(启动流程时生成的编码)
     */
    @TableField("FLOW_ID")
    private Long flowId;

    /**
     * 流程规则ID
     */
    @TableField("RULE_ID")
    private Long ruleId;

    /**
     * 流程状态(1:正常,0:异常)
     */
    @TableField("FLOW_STATE")
    private Integer flowState;

    /**
     * 流程启用时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;


}
