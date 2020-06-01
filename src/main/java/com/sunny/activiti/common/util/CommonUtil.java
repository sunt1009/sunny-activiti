package com.sunny.activiti.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * @ClassName: CommonUtil
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 10:31
 * @Version 1.0
 **/
public class CommonUtil {

    public static long genId() {
        return IdUtil.getSnowflake(0,0).nextId();
    }
}
