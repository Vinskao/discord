package com.mli.discord.module.message.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mli.discord.module.message.dao.MessageDAO;
import com.mli.discord.module.message.dto.MessageDTO;
import com.mli.discord.module.message.model.Message;

/**
 * 
 * @Author D3031104
 * @version 1.0
 */
@Service
public class MessageService {
    @Autowired
    private MessageDAO messageDAO;

    public Message saveMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setRoomId(messageDTO.getRoomId());
        message.setUsername(messageDTO.getUsername());
        message.setMessage(messageDTO.getMessage());
        message.setType(messageDTO.getType()); // 假设默认都是文本消息
        message.setTime(LocalDateTime.now());

        messageDAO.insertMessage(message);
        return message;
    }

    public List<Message> getMessagesByRoomId(Integer roomId) {
        return messageDAO.findMessagesByRoomId(roomId);
    }

}
