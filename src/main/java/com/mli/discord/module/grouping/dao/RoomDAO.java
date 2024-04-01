package com.mli.discord.module.grouping.dao;

import java.util.List;

import com.mli.discord.module.grouping.model.Room;

public interface RoomDAO {
    List<Room> findAllByGroupId(int groupId);
}
