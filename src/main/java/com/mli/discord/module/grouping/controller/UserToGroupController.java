package com.mli.discord.module.grouping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mli.discord.module.grouping.model.UserToGroup;
import com.mli.discord.module.grouping.service.UserToGroupService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user-to-group")
public class UserToGroupController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserToGroupService userToGroupService;

    /**
     * 將使用者加入群組
     *
     * @param userToGroup 要加入的使用者與群組資訊
     * @return HTTP 回應
     */
    @Operation(summary = "將使用者加入群組")
    @PostMapping("/add")
    public ResponseEntity<?> addUserToGroup(@RequestBody UserToGroup userToGroup) {
        try {
            int result = userToGroupService.addUserToGroup(userToGroup);
            if (result > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Failed to add user to group");
            }
        } catch (Exception e) {
            logger.error("發生錯誤: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred");
        }
    }

    /**
     * 從群組移除使用者
     *
     * @param userToGroup 要移除的使用者與群組資訊
     * @return HTTP 回應
     */
    @Operation(summary = "從群組移除使用者")
    @PostMapping("/remove")
    public ResponseEntity<?> removeUserFromGroup(@RequestBody UserToGroup userToGroup) {
        try {
            int result = userToGroupService.removeUserFromGroup(userToGroup);
            if (result > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Failed to remove user from group");
            }
        } catch (Exception e) {
            logger.error("發生錯誤: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred");
        }
    }
}
