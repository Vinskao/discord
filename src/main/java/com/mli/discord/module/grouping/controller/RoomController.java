package com.mli.discord.module.grouping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mli.discord.module.grouping.dto.GroupIdDTO;
import com.mli.discord.module.grouping.model.Room;
import com.mli.discord.module.grouping.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomService roomService;

    @PostMapping("/find-all-rooms")
    public ResponseEntity<?> getRoomsByGroupId(@RequestBody GroupIdDTO groupIdDTO) {

        try {
            List<Room> rooms = roomService.findAllRoomsByGroupId(groupIdDTO.getGroupId());
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            logger.error("Failed to retrieve rooms for the given group ID: {}", groupIdDTO.getGroupId(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to retrieve rooms for the given group ID");
        }
    }
}
