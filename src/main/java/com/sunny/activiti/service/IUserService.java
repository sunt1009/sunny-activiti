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
     * 获取当前登录的用户
     * @return
     */
    User getCurrentUser();

    /**
     * 根据用户名查询用户信息
     * @param userId
     * @return
     */
    User queryUserById(String userId);

    /**
     * 用户登录
     * @param userName
     * @param passWord
     * @return
     */
    ResponseResult<User> doLogin(String userName, String passWord);


}
