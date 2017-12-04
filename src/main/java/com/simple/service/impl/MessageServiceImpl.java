package com.simple.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;
import com.simple.dao.MessageMapper;
import com.simple.pojo.Message;
import com.simple.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by S I M P L E on 2017/12/03
 */
@Service("iMessageService")
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    public ServerResponse<Message> createNewNotice(String title, String message, String username) {
        int titleCount = messageMapper.checkTitle(title);
        if (titleCount > 0) {
            return ServerResponse.createByErrorMessage("标题已经存在");
        }
        int resultCount = messageMapper.insertNewNotice(title, message, username);
        if (resultCount > 0) {
            Message resultMessage = new Message();
            resultMessage.setTitle(title);
            resultMessage.setMessges(message);
            return ServerResponse.createBySuccess("插入成功",resultMessage);
        }
        return ServerResponse.createByErrorMessage("插入失败");
    }

    public ServerResponse<PageInfo> getMessageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> messageList = messageMapper.finalAll();
        PageInfo resultPage = new PageInfo(messageList);
        if (resultPage == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess("获取列表成功", resultPage);
    }

    public ServerResponse<String> deleteNote(String title) {
        int deleteCount = messageMapper.deleteNote(title);
        if (deleteCount > 0) {
            return ServerResponse.createBySuccess("删除消息成功");
        }
        return ServerResponse.createByErrorMessage("删除消息失败");
    }


}
