package com.mli.discord.module.grouping.dao;

import com.mli.discord.module.grouping.model.UserToGroup;

public interface UserToGroupDAO {
    Integer insertUserToGroup(UserToGroup userToGroup);

    Integer deleteUserFromGroup(UserToGroup userToGroup);
}
