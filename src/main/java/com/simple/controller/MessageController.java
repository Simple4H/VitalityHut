package com.simple.controller;

import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;
import com.simple.pojo.Message;
import com.simple.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Create by S I M P L E on 2017/12/03
 */
@RequestMapping(value = "/message/")
@Controller
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> createNewNotice(String title, String message) {
        ServerResponse response = iMessageService.createNewNotice(title, message);
        return response;
    }

    @RequestMapping(value = "get_list", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> getList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return iMessageService.getMessageList(pageNum, pageSize);
    }
}
