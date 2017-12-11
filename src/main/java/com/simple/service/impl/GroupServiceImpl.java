package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.GroupMapper;
import com.simple.pojo.Group;
import com.simple.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //加入小组
    public ServerResponse<String> joinGroup(String username) {
        //检验用户是不已经存在小组中
        List existUser = groupMapper.checkUserExist(username);
        if (existUser.size() != 0) {
            return ServerResponse.createByErrorMessage("用户已经在小组中");
        }
        //查询一开始的已经加入的小组成员
        String originalUser = groupMapper.getGroupUser();
        if (originalUser == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        //添加成员到小组中,用","分隔开来
        String updateUser = originalUser + "," + username;
        //重新插入数据表
        int result = groupMapper.joinGroup(updateUser, originalUser);
        if (result != 0) {
            return ServerResponse.createBySuccessMessage("加入成功");
        }
        return ServerResponse.createByErrorMessage("加入失败");
    }
}
