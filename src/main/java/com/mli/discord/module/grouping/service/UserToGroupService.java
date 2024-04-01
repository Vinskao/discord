package com.mli.discord.module.grouping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mli.discord.module.grouping.dao.UserToGroupDAO;
import com.mli.discord.module.grouping.model.UserToGroup;

@Service
public class UserToGroupService {
    @Autowired
    private UserToGroupDAO userToGroupDAO;

    public Integer addUserToGroup(UserToGroup userToGroup) {
        return userToGroupDAO.insertUserToGroup(userToGroup);
    }

    public Integer removeUserFromGroup(UserToGroup userToGroup) {
        return userToGroupDAO.deleteUserFromGroup(userToGroup);
    }
}
