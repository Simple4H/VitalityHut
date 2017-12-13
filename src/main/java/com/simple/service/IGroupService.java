package com.simple.service;

import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;

/**
 * Create by S I M P L E on 2017/12/11
 */
public interface IGroupService {

    ServerResponse<String> createGroup(String title, String message, String username);

    ServerResponse<String> joinGroup(String username, String title);

    ServerResponse<PageInfo> getGroupList(int pageNum, int pageSize);

    ServerResponse<PageInfo> getGroupListByUser(String username, int pageNum, int pageSize);

    ServerResponse<PageInfo> findBlurMessage(String messageCondition, int pageNum, int pageSize);

    ServerResponse<PageInfo> findBlurTitle(String titleCondition, int pageNum, int pageSize);
}