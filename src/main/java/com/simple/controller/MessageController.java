package com.simple.controller;

import com.github.pagehelper.PageInfo;
import com.simple.common.Const;
import com.simple.common.ResponseCode;
import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


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


    //创建通知
    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse createNewNotice(@RequestBody Map map, HttpSession session) {
        String title = (String) map.get("title");
        String message = (String) map.get("message");
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录，无法创建新的通知");
        }
        return iMessageService.createNewNotice(title, message, currentUser.getUsername());
    }

    //获取所有的通知
    @RequestMapping(value = "get_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> getList(@RequestBody Map map) {
        int pageNum = (Integer) map.get("pageNum");
        int pageSize = (Integer) map.get("pageSize");

        return iMessageService.getMessageList(pageNum, pageSize);
    }

    //删除通知
    @RequestMapping(value = "delete_note.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteNote(@RequestBody Map map, HttpSession session) {
        String title = (String) map.get("title");
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,需要登录");
        }
        int resultCount = userMapper.checkAdmin(currentUser.getUsername());
        System.out.println(resultCount);
        if (resultCount != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createByErrorMessage("不是管理员无法删除");
        }
        return iMessageService.deleteNote(title);
    }

    //通过信息模糊查询
    @RequestMapping(value = "find_blur_message.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> findBlurMessage(@RequestBody Map map, HttpSession session) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String messageCondition = (String) map.get("messageCondition");
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录,请登录");
        }
        return iMessageService.findBlurMessage(messageCondition, pageNum, pageSize);
    }

    //通过标题模糊查询
    @RequestMapping(value = "find_blur_title.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> findBlurTitle(@RequestBody Map map, HttpSession session) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = (Integer) map.get("pageSize");
        String titleCondition = (String) map.get("titleCondition");
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("没有登录,请登录");
        }
        return iMessageService.findBlurTitle(titleCondition, pageNum, pageSize);
    }
}
