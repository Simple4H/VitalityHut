package com.simple.dao;

import com.simple.pojo.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    List checkTitleExist(String title);

    int createNewGroup(@Param("title") String title, @Param("message") String message, @Param("username") String username);

    String getGroupUser(String title);

    int joinGroup(@Param("updateUser") String updateUser, @Param("originalUser") String originalUser);

    List checkUserExist(@Param("title") String title, @Param("username") String username);

    int checkGroupTile(String title);

    List<Group> getGroupList();

    List<Group> getGroupListByUser(String username);

    List<Group> findBlurMessage(String messageCondition);

    List<Group> findBlurTitle(String titleCondition);

    Group getCurrentGroupMessage(String title);

}