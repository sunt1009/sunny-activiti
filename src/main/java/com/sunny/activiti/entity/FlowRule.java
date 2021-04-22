package com.sunny.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 流程规则表
 * </p>
 *
 * @author sunt
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_FLOW_RULE")
public class FlowRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 规则ID
     */
    @TableId("RULE_ID")
    private Long ruleId;

    /**
     * 业务流程定义ID
     */
    @TableField("DEF_ID")
    private Long defId;

    /**
     * 系统来源
     */
    @TableField("SYSTEM_CODE")
    private String systemCode;

    /**
     * 业务类型
     */
    @TableField("BUSI_TYPE")
    private String busiType;

    /**
     * 规则名称
     */
    @TableField("RULE_NAME")
    private String ruleName;

    /**
     * 规则描述
     */
    @TableField("RULE_DESC")
    private String ruleDesc;

    @TableField(exist = false)
    private List<String> systemIds;

    @TableField(exist = false)
    private List<String> busiTypes;

    @TableField(exist = false)
    private String defName;


}
