package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.GroupMapper;
import com.simple.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by S I M P L E on 2017/12/11
 */
@Service("iGroupService")
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    public ServerResponse<String> createGroup(String title, String message, String username) {
        int result = groupMapper.createNewGroup(title, message, username);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("新建小组成功");
        }
        return ServerResponse.createByErrorMessage("新建小组失败");
    }


}
