package com.simple.dao;

import com.simple.pojo.Group;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    int createNewGroup(@Param("title")String title,@Param("message")String message,@Param("username")String username);

}