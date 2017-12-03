package com.simple.service;

import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;


/**
 * Create by S I M P L E on 2017/12/03
 */
public interface IMessageService {

    ServerResponse<String> createNewNotice(String title, String message);

    ServerResponse<PageInfo> getMessageList(int pageNum, int pageSize);

}
