package com.mli.discord.module.message.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mli.discord.module.message.dto.MessageDTO;
import com.mli.discord.module.message.dto.RoomIdDTO;
import com.mli.discord.module.message.model.Message;
import com.mli.discord.module.message.service.MessageService;

/**
 * @Author D3031104
 * @Version 1.0
 *          接收Client送來的WebSocket訊息及推送給前端的訊息
 */

@RestController
public class ChatController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageService messageService;

	@Autowired
	SimpMessagingTemplate template;

	/**
	 * 發送消息給 WebSocket 客戶端
	 * 
	 * @param textMessageDTO 待發送的消息
	 * @return ResponseEntity<Void>
	 */
	@PostMapping("/send")
	public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO textMessageDTO) {
		// Log received message
		System.out.println("Received message: " + textMessageDTO.getMessage());

		// Broadcast message
		template.convertAndSend("/topic/message", textMessageDTO);

		// Log after broadcasting
		System.out.println("Message broadcasted to /topic/message");

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 接收 WebSocket 客戶端發送的消息
	 * 
	 * @param textMessageDTO 接收到的消息
	 */
	@MessageMapping("/sendMessage")
	public void receiveMessage(@Payload MessageDTO textMessageDTO) {
		// receive message from client
	}

	/**
	 * 將消息廣播給 WebSocket 客戶端
	 * 
	 * @param messageDTO 待廣播的消息
	 * @return MessageDTO
	 */
	@SendTo("/topic/message")
	public MessageDTO broadcastMessage(@Payload MessageDTO messageDTO) {
		// 日誌記錄，以便於調試和監控
		logger.info("Broadcasting message from {}: {}", messageDTO.getUsername(), messageDTO.getMessage());

		// 將消息廣播到 /topic/message
		template.convertAndSend("/topic/message", messageDTO);

		return messageDTO;
	}

	/**
	 * 處理從前端 STOMP 客戶端發送到 /app/message 的消息
	 * 
	 * @param messageDTO     接收到的消息
	 * @param headerAccessor 消息頭訪問器
	 */
	@MessageMapping("/message")
	public void receiveAndBroadcastMessage(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
		Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
		if (sessionAttributes == null) {
			logger.info("Session attributes are null");
			return;
		}

		if (!sessionAttributes.containsKey("username")) {
			logger.info("Username is not found in WebSocket session attributes.");
			return;
		}

		// 從sessionAttributes中獲取username，並處理它
		String username = (String) sessionAttributes.get("username");
//		String username = fullUsername.split("@")[0]; // 僅獲取@之前的部分

		messageDTO.setUsername(username);
		logger.info("Received STOMP message from {}: {}", username, messageDTO.getMessage());
		Message message = messageService.saveMessage(messageDTO);
		template.convertAndSend("/topic/message", message);
		logger.info("STOMP Message broadcasted to /topic/message");

	}

	/**
	 * 根據房間ID獲取消息
	 * 
	 * @param roomIdDTO 房間IDDTO
	 * @return ResponseEntity<List<Message>> 消息列表
	 */
	@PostMapping("/get-messages")
	public ResponseEntity<List<Message>> getMessagesByRoomId(@RequestBody RoomIdDTO roomIdDTO) {
		List<Message> messages = messageService.getMessagesByRoomId(roomIdDTO.getRoomId());
		return ResponseEntity.ok(messages);
	}
}
