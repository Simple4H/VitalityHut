package com.simple.dao;

import com.simple.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User userLogin(@Param("username") String username, @Param("password") String password);

    int checkPassword(@Param("password") String password, @Param("userId") int userId);

    int checkEmailByUserId(@Param("email") String email, @Param("userId") int userId);

    int checkQuestionAndAnswer(@Param("username") String username, @Param("question") String password, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("newPassword") String newPassword);

    int checkEmail(String email);

    int checkAdmin(String username);

    String getQuestion(String username);
}