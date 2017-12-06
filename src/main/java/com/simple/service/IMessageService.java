package com.simple.service;

import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;
import com.simple.pojo.Message;


/**
 * Create by S I M P L E on 2017/12/03
 */
public interface IMessageService {

    ServerResponse<Message> createNewNotice(String title, String message, String username);

    ServerResponse<PageInfo> getMessageList(int pageNum, int pageSize);

    ServerResponse<String> deleteNote(String title);

    ServerResponse<PageInfo> findBlur(String condition, int pageNum, int pageSize);

}
