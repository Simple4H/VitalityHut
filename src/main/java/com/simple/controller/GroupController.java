package com.simple.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.Group;
import com.simple.pojo.User;
import com.simple.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

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

    //管理院创建新的小组
    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> createGroup(@RequestBody Map map, HttpSession session) {
        String title = (String) map.get("title");
        String message = (String) map.get("message");
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

    //用户进入小组
    @RequestMapping(value = "join_group.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> joinGroup(@RequestBody Map map, HttpSession session) {
        String title = (String) map.get("title");
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }
        return iGroupService.joinGroup(user.getUsername(), title);
    }

    //获取小组的列表
    @RequestMapping(value = "get_group_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> getGroupList(@RequestBody Map map, HttpSession session) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }
        return iGroupService.getGroupList(pageNum, pageSize);
    }

    //获取当前用户已经加入小组的列表
    @RequestMapping(value = "get_group_list_by_user.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> getGroupListByUser(@RequestBody Map map, HttpSession session) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }
        return iGroupService.getGroupListByUser(user.getUsername(), pageNum, pageSize);
    }

    //通过信息模糊查询
    @RequestMapping(value = "find_blur_message.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> findBlurMessage(@RequestBody Map map, HttpSession session) {
        int pageNum = (Integer) map.get("pageNum");
        int pageSize = (Integer) map.get("pageSize");
        String messageCondition = (String) map.get("messageCondition");
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录,请登录");
        }
        return iGroupService.findBlurMessage(messageCondition, pageNum, pageSize);
    }

    //通过标题模糊查询
    @RequestMapping(value = "find_blur_title.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> findBlurTitle(@RequestBody Map map, HttpSession session) {
        int pageNum = (Integer) map.get("pageNum");
        int pageSize = (Integer) map.get("pageSize");
        String titleCondition = (String) map.get("titleCondition");
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录,请登录");
        }
        return iGroupService.findBlurTitle(titleCondition, pageNum, pageSize);
    }

    //获取加入小组的信息
    @RequestMapping(value = "get_current_group_message.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Group> getCurrentGroupMessage(@RequestBody Map map, HttpSession session) {
        String title = (String) map.get("title");
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录,请登录");
        }
        return iGroupService.getCurrentGroupMessage(title);
    }
}