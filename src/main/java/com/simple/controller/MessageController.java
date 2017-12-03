package com.simple.controller;

import com.simple.common.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by S I M P L E on 2017/12/03
 */
@RequestMapping(value = "/message/")
@Controller
public class MessageController {

    public ServerResponse<String> insertMessage(String title,String message) {
        return null;
    }
}
