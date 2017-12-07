package com.simple.dao;

import com.simple.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    int checkTitle(String title);

    int insertNewNotice(@Param("title") String title, @Param("message") String message, @Param("author") String author);

    List<Message> finalAll();

    int deleteNote(String title);

    List<Message> findBlurMessage(String messageCondition);

    List<Message> findBlurTitle(String titleCondition);
}