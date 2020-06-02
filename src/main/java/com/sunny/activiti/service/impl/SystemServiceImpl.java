package com.sunny.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.activiti.common.util.TreeUtil;
import com.sunny.activiti.entity.MenuVo;
import com.sunny.activiti.entity.SysDict;
import com.sunny.activiti.entity.SystemMenu;
import com.sunny.activiti.mapper.SysDictMapper;
import com.sunny.activiti.mapper.SystemMenuMapper;
import com.sunny.activiti.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysMenuServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/28 20:25
 * @Version 1.0
 **/
@Service
public class SystemServiceImpl implements ISystemService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public Map<String, Object> queryMenuList() {

        Map<String, Object> map = new HashMap<>(16);
        Map<String,Object> home = new HashMap<>(16);
        Map<String,Object> logo = new HashMap<>(16);

        //查询状态为启用的菜单
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",1);
        queryWrapper.orderByAsc("sort");
        List<SystemMenu> menuList = systemMenuMapper.selectList(queryWrapper);

        List<MenuVo> menuInfo = new ArrayList<>();
        for (SystemMenu e : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getId());
            menuVO.setPid(e.getPid());
            menuVO.setHref(e.getHref());
            menuVO.setTitle(e.getTitle());
            menuVO.setIcon(e.getIcon());
            menuVO.setTarget(e.getTarget());
            menuInfo.add(menuVO);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0));
        home.put("title","首页");
        home.put("href","/page/welcome-1");//控制器路由,自行定义
        logo.put("title","activiti工作流");
        logo.put("image","images/logo.png");//静态资源文件路径,可使用默认的logo.png
        map.put("homeInfo", home);
        map.put("logoInfo", logo);
        return map;
    }

    @Override
    public List<SysDict> querySysDictInfo(int dictTypeCode) {
        return sysDictMapper.querySysDictInfo(dictTypeCode);
    }
}
