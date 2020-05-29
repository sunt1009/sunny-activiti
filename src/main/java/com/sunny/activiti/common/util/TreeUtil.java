package com.sunny.activiti.common.util;

import com.sunny.activiti.entity.MenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeUtil
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/28 20:11
 * @Version 1.0
 **/
public class TreeUtil {

    public static List<MenuVo> toTree(List<MenuVo> treeList, Integer pid) {
        List<MenuVo> retList = new ArrayList<MenuVo>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }
    private static MenuVo findChildren(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
}
