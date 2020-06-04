package com.sunny.activiti.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import com.sunny.activiti.common.entity.ResultCode;
import com.sunny.activiti.common.entity.SysConstant;
import com.sunny.activiti.entity.User;
import com.sunny.activiti.mapper.UserMapper;
import com.sunny.activiti.service.ICacheService;
import com.sunny.activiti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunt
 * @since 2020-05-18
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ICacheService cacheService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getCurrentUser() {
        Cookie cookie = ServletUtil.getCookie(request, SysConstant.ACTIVITI_COOKIE.toLowerCase());
        if(ObjectUtil.isNull(cookie)) {
            return null;
        }
        String loginToken = cookie.getValue();
        User cacheUser = (User) cacheService.getObjCacheByCode(loginToken);
        if(ObjectUtil.isNull(cacheUser)) {
            return null;
        }
        return cacheUser;
    }

    @Override
    public User queryUserById(String userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_ID",userId);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public ResponseResult<User> doLogin(String userName, String passWord) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_NAME",userName);
        User user = userMapper.selectOne(queryWrapper);
        if(ObjectUtil.isNull(user)) {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,"用户不存在");
        }
        if(!StrUtil.equals(user.getUserPass(), SecureUtil.md5(passWord))) {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,"用户名或密码错误");
        }
        return ResponseUtil.makeOKRsp(user);
    }

}
