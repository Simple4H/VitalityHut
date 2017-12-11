package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Create by S I M P L E on 2017/12/11
 */
@RequestMapping(value = "/group/")
@Controller
public class GroupController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IGroupService iGroupService;

    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> createGroup(String title, String message, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }
        int resultAdmin = userMapper.checkAdmin(user.getUsername());
        if (resultAdmin == 0) {
            return ServerResponse.createByErrorMessage("不是管理员无法创建新的小组");
        }
        return iGroupService.createGroup(title, message, user.getUsername());
    }

    @RequestMapping(value = "join_group.do")
    @ResponseBody
    public ServerResponse<String> joinGroup(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }
        return iGroupService.joinGroup(user.getUsername());
    }
}
