package com.mli.discord.module.grouping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mli.discord.module.grouping.dao.RoomDAO;
import com.mli.discord.module.grouping.model.Room;

@Service
public class RoomService {
    @Autowired
    private RoomDAO roomDAO;

    public List<Room> findAllRoomsByGroupId(int groupId) {
        return roomDAO.findAllByGroupId(groupId);
    }
}
