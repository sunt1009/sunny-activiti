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
     * 订单相关页面跳转
     * @param page
     * @return
     */
    @GetMapping("/order/{page}")
    public String toOrdPage(@PathVariable String page) {
        return "order/" + page;
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

    /**
     * 跳转商品相关页面
     * @param page
     * @return
     */
    @GetMapping("/goods/{page}")
    public String toGoodsPage(@PathVariable String page) {
        return "goods/" + page;
    }

    /**
     * 跳转测试相关页面
     * @param page
     * @return
     */
    @GetMapping("/test/{page}")
    public String toTestPage(@PathVariable String page) {
        return "test/" + page;
    }
}
