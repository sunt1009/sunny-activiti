package com.sunny.activiti.service;

import com.sunny.activiti.entity.SysDict;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ISysMenuService
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/28 20:24
 * @Version 1.0
 **/
public interface ISystemService {

    /**
     * 查询菜单列表
     * @return
     */
    Map<String, Object> queryMenuList();

    /**
     * 查询字典信息
     * @param dictTypeCode
     * @return
     */
    List<SysDict> querySysDictInfo(int dictTypeCode);
}
