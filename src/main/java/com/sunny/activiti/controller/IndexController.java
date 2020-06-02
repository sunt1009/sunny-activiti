package com.sunny.activiti.controller;

import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import com.sunny.activiti.common.entity.SysConstant;
import com.sunny.activiti.entity.SysDict;
import com.sunny.activiti.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexController
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/28 19:43
 * @Version 1.0
 **/
@RestController
public class IndexController {

    @Autowired
    private ISystemService systemService;

    @RequestMapping("queryMenu")
    public Map<String, Object> queryMenu() {
        Map<String, Object> map = systemService.queryMenuList();
        return map;
    }

    @RequestMapping("querySysDict")
    public ResponseResult<List<SysDict>> querySysDict() {
        List<SysDict> sysDicts = systemService.querySysDictInfo(SysConstant.SYSTEM_CODE);
        return ResponseUtil.makeOKRsp(sysDicts);
    }
}
