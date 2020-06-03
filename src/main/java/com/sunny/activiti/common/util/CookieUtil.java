package com.sunny.activiti.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.sunny.activiti.common.entity.SysConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CookieUtil
 * @Description: cookie操作工具类
 * @Author: sunt
 * @Date: 2019/8/13 10:53
 * @Version 1.0
 **/
public class CookieUtil {

    /**
     * 获取cookie的key值
     * @param request
     * @return
     */
    public static String getCookieStr(HttpServletRequest request) {
        String cookieSt = "";
        Cookie cookie = ServletUtil.getCookie(request, SysConstant.ACTIVITI_COOKIE.toLowerCase());
        if(ObjectUtil.isNull(cookie)) {
            return cookieSt;
        }
        cookieSt = cookie.getValue();
        return cookieSt;
    }

    /**
     * cookie立即失效
     * @param response
     * @param cookieStr
     */
    public static void delCookie(HttpServletResponse response, String cookieStr) {
        ServletUtil.addCookie(response,SysConstant.ACTIVITI_COOKIE,cookieStr,0);
    }
}
