package com.sunny.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunt
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SYS_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;

    @TableField("P_USER_ID")
    private String parentUserId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("USER_PASS")
    private String userPass;

    @TableField("USER_PHOTO")
    private String userPhoto;

    @TableField("USER_NICK")
    private String userNick;

    @TableField("USER_SIGN")
    private String userSign;

    @TableField(exist = false)
    private String resMsg;

}
