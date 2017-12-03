package com.simple.service.impl;

import com.simple.dao.MessageMapper;
import com.simple.service.IMessageService;
import org.springframework.stereotype.Service;

/**
 * Create by S I M P L E on 2017/12/03
 */
@Service("iMessageService")
public class MessageServiceImpl implements IMessageService {

    private MessageMapper messageMapper;


}
