package com.simple.service.impl;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.common.TokenCache;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Create by S I M P L E on 2017/12/02
 */

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    //登录
    public ServerResponse<User> login(String username, String password) {
        int result = userMapper.checkUsername(username);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        User user = userMapper.userLogin(username, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        return ServerResponse.createBySuccess("登录成功", user);
    }

    //登录状态下重置密码
    public ServerResponse<String> resetPassword(String newPassword, String oldPassword, User user) {
        int resultCount = userMapper.checkPassword(oldPassword, user.getId());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(newPassword);
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createByErrorMessage("更新密码成功");
        }
        return ServerResponse.createByErrorMessage("更新密码错误");
    }

    //更新个人信息
    public ServerResponse<User> updateInformation(User user) {
        //检验邮箱是否存在
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("邮箱已经存在，请更换邮箱");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("更新成功", updateUser);
        } else {
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }

    //忘记密码
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkQuestionAndAnswer(username, question, answer);
        if (resultCount > 0) {
            //说明问题及问题答案是这个用户的,并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }

    public ServerResponse<String> forgetResetPassword(String username, String NewPassword, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误,token需要传递");
        }
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }

        if (StringUtils.equals(forgetToken, token)) {
            int rowCount = userMapper.updatePasswordByUsername(username, NewPassword);
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    //检验用户名和邮箱的
    private ServerResponse<String> checkValid(String str, String type) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }
}
