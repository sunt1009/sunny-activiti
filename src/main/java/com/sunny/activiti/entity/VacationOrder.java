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
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 假期管理
 * </p>
 *
 * @author sunt
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_VACATION_ORDER")
public class VacationOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("VACATION_ID")
    private Long vacationId;

    /**
     * 请假人ID
     */
    @TableId("USER_ID")
    private String userId;

    /**
     * 开始时间
     */
    @TableField("START_TIME")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @TableField("END_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    /**
     * 请假类型
     */
    @TableField("VACATION_TYPE")
    private Integer vacationType;

    /**
     * 请假原因
     */
    @TableField("VACATION_CONTEXT")
    private String vacationContext;

    /**
     * 申请状态(0:待提交 1:审核中 2:已废弃 3:已完成)
     */
    @TableField("VACATION_STATE")
    private Integer vacationState;

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
     * 创建时间
     */
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern=DatePattern.NORM_DATETIME_PATTERN,timezone="GMT+8")
    @TableField("CREATE_TIME")
    private Date createTime;

}
