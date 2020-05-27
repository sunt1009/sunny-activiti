package com.sunny.activiti.controller;

import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: sunt
 * @Date: 2020/5/27 10:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("exception")
    public ResponseResult<String> testException() {
        int num = 1/0;
        return ResponseUtil.makeOKRsp("测试成功");
    }
}
