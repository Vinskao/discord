package com.mli.discord.module.grouping.dao;

import java.util.List;

import com.mli.discord.module.grouping.model.UserToRoom;

public interface UserToRoomDAO {
    Integer insertUserToRoom(UserToRoom userToRoom);

    Integer deleteUserFromRoom(UserToRoom userToRoom);

    List<UserToRoom> selectUsersByRoomId(Integer roomId);

    Integer existsByUsernameAndRoomId(String username, Integer roomId);

}