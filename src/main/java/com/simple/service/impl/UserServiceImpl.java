package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by S I M P L E on 2017/12/02
 */

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String username, String password) {
        int result = userMapper.checkUsername(username);
        if (result == 0) {
            return ServerResponse.createByError("用户名不存在");
        }
        User user = userMapper.userLogin(username, password);
        if (user == null) {
            return ServerResponse.createByError("密码错误");
        }
        return ServerResponse.createBySuccess("登录成功", user);
    }

    public ServerResponse<String> resetPassword(String newPassword, String oldPassword, User user) {
        int resultCount = userMapper.checkPassword(oldPassword, user.getId());
        if (resultCount == 0) {
            return ServerResponse.createByError("旧密码错误");
        }
        user.setPassword(newPassword);
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("更新密码成功");
        }
        return ServerResponse.createBySuccess("更新密码错误");

    }
}
