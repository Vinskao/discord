package com.mli.discord.module.grouping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mli.discord.module.grouping.dto.RoomIdDTO;
import com.mli.discord.module.grouping.model.UserToRoom;
import com.mli.discord.module.grouping.service.UserToRoomService;

@RestController
@RequestMapping("/user-to-room")
public class UserToRoomController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserToRoomService userToRoomService;

    @PostMapping("/add")
    public ResponseEntity<?> addUserToRoom(@RequestBody UserToRoom userToRoom) {
        try {
            int rowsAffected = userToRoomService.addUserToRoom(userToRoom);
            if (rowsAffected > 0) {
                return ResponseEntity.ok().body("User added to room successfully");
            } else {
                logger.warn("Failed to add user to room: {}", userToRoom);
                return ResponseEntity.badRequest().body("Failed to add user to room");
            }
        } catch (Exception e) {
            logger.error("Exception adding user to room: {}", userToRoom, e);
            return ResponseEntity.badRequest().body("Failed to add user to room");
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeUserFromRoom(@RequestBody UserToRoom userToRoom) {
        try {
            int rowsAffected = userToRoomService.removeUserFromRoom(userToRoom);
            if (rowsAffected > 0) {
                return ResponseEntity.ok().body("User removed from room successfully");
            } else {
                logger.warn("Failed to remove user from room: {}", userToRoom);
                return ResponseEntity.badRequest().body("Failed to remove user from room");
            }
        } catch (Exception e) {
            logger.error("Exception removing user from room: {}", userToRoom, e);
            return ResponseEntity.badRequest().body("Failed to remove user from room");
        }
    }

    @PostMapping("/get-by-room")
    public ResponseEntity<List<UserToRoom>> getUsersByRoomId(@RequestBody RoomIdDTO roomIdDTO) {
        List<UserToRoom> users = userToRoomService.getAllUserToRooms(roomIdDTO.getRoomId());
        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
