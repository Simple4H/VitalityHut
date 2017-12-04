package com.simple.controller;

import com.github.pagehelper.PageInfo;
import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * Create by S I M P L E on 2017/12/03
 */
@RequestMapping(value = "/message/")
@Controller
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse createNewNotice(String title, String message, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录，无法创建新的通知");
        }
        return iMessageService.createNewNotice(title, message, currentUser.getUsername());
    }

    @RequestMapping(value = "get_list", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> getList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return iMessageService.getMessageList(pageNum, pageSize);
    }

    @RequestMapping(value = "delete_note.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteNote(String title, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录，无法删除");
        }
        int resultCount = userMapper.checkAdmin(currentUser.getUsername());
        System.out.println(resultCount);
        if (resultCount != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createByErrorMessage("不是管理员无法删除");
        }
        return iMessageService.deleteNote(title);
    }
}
