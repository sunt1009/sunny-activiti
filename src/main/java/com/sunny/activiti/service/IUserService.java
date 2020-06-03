package com.sunny.activiti.service;

import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunt
 * @since 2020-05-18
 */
public interface IUserService {

    /**
     * 根据用户名查询对应的角色和权限信息
     * @param userName
     * @return
     */
    User queryUserInfo(String userName);

    /**
     * 用户登录
     * @param userName
     * @param passWord
     * @return
     */
    ResponseResult<User> doLogin(String userName, String passWord);


}
