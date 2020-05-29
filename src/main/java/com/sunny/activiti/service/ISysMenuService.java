package com.sunny.activiti.service;

import java.util.Map;

/**
 * @ClassName: ISysMenuService
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/28 20:24
 * @Version 1.0
 **/
public interface ISysMenuService {

    /**
     * 查询菜单列表
     * @return
     */
    Map<String, Object> queryMenuList();
}
