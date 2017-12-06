package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Create by S I M P L E on 2017/12/02
 */

@RequestMapping(value = "/user/")
@Controller
public class UserController {

    @Autowired
    private IUserService iUserService;

    //登录
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestBody User user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    //获取用户信息
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }

    //登录状态修改密码
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String newPassword, String oldPassword) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iUserService.resetPassword(newPassword, oldPassword, user);
    }

    //更新用户信息
    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录，无法更新信息");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if (response.isSuccess()) {
            //获取username,并且放入response对象中
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    //问题和答案
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(@RequestBody User user) {
        String username = user.getUsername();
        String question = user.getQuestion();
        String answer = user.getAnswer();
        return iUserService.checkAnswer(username, question, answer);
    }

//    //问题和答案
//    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer) {
//        return iUserService.checkAnswer(username, question, answer);
//    }


    //忘记密码下重置密码
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(@RequestBody Map map) {
        String username = (String) map.get("username");
        String newPassword = (String) map.get("newPassword");
        String token = (String) map.get("token");
        return iUserService.forgetResetPassword(username, newPassword, token);
    }

    //获取问题
    @RequestMapping(value = "get_question_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> getQuestionInformation(@RequestBody User user) {
        String username = user.getUsername();
        System.out.println(username);
        return iUserService.getQuestionInformation(username);
    }

    //上传文件
    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> upload(@RequestParam("upload_file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                String rootPath = "f:/VitalityHut";
                File dir = new File(rootPath + File.separator + "file");
                if (!dir.exists())
                    dir.mkdirs();
                //重新命名上传文件的名字
                Date now = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                String nowTime = simpleDateFormat.format(now);
                String rename = nowTime + "_" + file.getOriginalFilename();
                File serverFile = new File(dir.getAbsolutePath() + File.separator + rename);
                // 写文件到服务器
                file.transferTo(serverFile);
                return ServerResponse.createBySuccessMessage("成功上传" + file.getOriginalFilename());
            } catch (Exception e) {
                return ServerResponse.createByErrorMessage("失败上传 " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            return ServerResponse.createByErrorMessage("失败上传 " + file.getOriginalFilename() + " 是空的");
        }
    }
    // TODO: 不要添加@RequestParam
}
