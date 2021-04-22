package com.sunny.activiti.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: MenuVo
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/28 20:12
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MenuVo {

    private Integer id;

    private Integer pid;

    private String title;

    private String icon;

    private String href;

    private String target;

    private List<MenuVo> child;
}
