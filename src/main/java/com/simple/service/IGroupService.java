package com.simple.service;

import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;

/**
 * Create by S I M P L E on 2017/12/11
 */
public interface IGroupService {

    ServerResponse<String> createGroup(String title, String message, String username);

    ServerResponse<String> joinGroup(String username,String title);

    ServerResponse<PageInfo> getGroupList(String username, int pageSize, int pageNum);
}
