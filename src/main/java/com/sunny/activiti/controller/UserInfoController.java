package com.sunny.activiti.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import com.sunny.activiti.common.entity.ResultCode;
import com.sunny.activiti.common.entity.SysConstant;
import com.sunny.activiti.common.util.CookieUtil;
import com.sunny.activiti.entity.User;
import com.sunny.activiti.service.ICacheService;
import com.sunny.activiti.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/3 10:07
 * @Version 1.0
 **/
@RequestMapping("user")
@RestController
@Slf4j
public class UserInfoController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICacheService cacheService;

    /**
     * 登录
     * @param user
     * @param response
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<User> doLogin(@RequestBody User user, HttpServletResponse response) {
        ResponseResult<User> userResponseResult = userService.doLogin(user.getUserName(), user.getUserPass());

        String token = IdUtil.fastSimpleUUID();
        ServletUtil.addCookie(response, SysConstant.ACTIVITI_COOKIE, token,-1); //关闭浏览器登录失效
        cacheService.cacheObjData(token,userResponseResult.getData(), 60);
        return userResponseResult;
    }


    /**
     * 获取登录信息
     * @return
     */
    @RequestMapping("getLoginInfo")
    public ResponseResult<User> getLoginInfo() {
        User currentUser = userService.getCurrentUser();
        if(ObjectUtil.isNull(currentUser)) {
            return ResponseUtil.makeErrRsp(ResultCode.NOT_LOGIN.code,"登录失效");
        }
        return ResponseUtil.makeOKRsp(currentUser);
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/outLogin")
    public ResponseResult<Object> outLogin(HttpServletRequest request,HttpServletResponse response) {
        try {
            //获取token
            String cookieStr = CookieUtil.getCookieStr(request);
            boolean delCacheRes = cacheService.delCacheByCode(cookieStr);
            log.info("删除缓存key:" + cookieStr + "，返回结果:" + delCacheRes);
            CookieUtil.delCookie(response,cookieStr);
            return ResponseUtil.makeOKRsp("注销成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,"注销异常:" + e.getMessage());
        }
    }
}
