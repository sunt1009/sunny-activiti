package com.sunny.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: PageController
 * @Description: 页面跳转
 * @Author: sunt
 * @Date: 2019/7/2 22:19
 * @Version 1.0
 **/
@Controller
public class PageController {

    /**
     * @return java.lang.String
     * @Author sunt
     * @Description 跟路径跳转
     * @Date 2019/7/2 22:20
     * @Param [page]
     **/
    @GetMapping("/{page}")
    public String toPage(@PathVariable String page) {
        return page;
    }


    /**
     * 跳转流程相关页面
     * @param page
     * @return
     */
    @GetMapping("/model/{page}")
    public String toModelPage(@PathVariable String page) {
        return "model/" + page;
    }

}
