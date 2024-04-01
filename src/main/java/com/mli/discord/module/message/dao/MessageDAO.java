package com.mli.discord.module.message.dao;


import java.util.List;

import com.mli.discord.module.message.model.Message;

/**
 * Message DAO MyBatis Mapper
 * 
 * @Author D3031104
 * @version 1.0
 */
public interface MessageDAO {
	void insertMessage(Message message);
	List<Message> findMessagesByRoomId(Integer roomId);

}
