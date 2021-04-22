package com.sunny.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典类型值表
 * </p>
 *
 * @author sunt
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @TableId("DICT_ID")
    private String dictId;

    /**
     * 类型编码
     */
    @TableField("DICT_TYPE_CODE")
    private String dictTypeCode;

    /**
     * 字典名(展示用)
     */
    @TableField("DICT_NAME")
    private String dictName;

    /**
     * 字典值
     */
    @TableField("DICT_VALUE")
    private String dictValue;

    @TableField(exist = false)
    private String dictTypeName;

}
