package com.mli.discord.module.grouping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mli.discord.module.grouping.model.Group;
import com.mli.discord.module.grouping.service.GroupService;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GroupService groupService;

    @PostMapping("/find-all-groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        try {
            List<Group> groups = groupService.getAllGroups();
            if (groups.isEmpty()) {
                logger.info("No groups found");
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(groups);
        } catch (Exception e) {
            logger.error("Failed to retrieve groups", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
