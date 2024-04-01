package com.mli.discord.module.grouping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mli.discord.module.grouping.dao.GroupDAO;
import com.mli.discord.module.grouping.model.Group;

@Service
public class GroupService {
    @Autowired
    private GroupDAO groupDAO;

    public List<Group> getAllGroups() {
        return groupDAO.selectAllGroups();
    }
}
