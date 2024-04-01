package com.mli.discord.module.grouping.dao;

import java.util.List;

import com.mli.discord.module.grouping.model.Group;

public interface GroupDAO {
    List<Group> selectAllGroups();
}
