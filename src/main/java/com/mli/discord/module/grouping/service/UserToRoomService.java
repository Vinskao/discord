package com.mli.discord.module.grouping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mli.discord.module.grouping.dao.UserToRoomDAO;
import com.mli.discord.module.grouping.model.UserToRoom;

@Service
public class UserToRoomService {
    @Autowired
    private UserToRoomDAO userToRoomDAO;

    public Integer addUserToRoom(UserToRoom userToRoom) {
        // 检查用户是否已在房间中
        Integer count = checkIfUserInRoom(userToRoom.getUsername(), userToRoom.getRoomId());
        if (count == 0) {
            // 如果用户不在房间中，则插入新记录
            return userToRoomDAO.insertUserToRoom(userToRoom);
        } else {
            // 用户已在房间中
            return 0; // 没有行被影响
        }
    }

    public Integer removeUserFromRoom(UserToRoom userToRoom) {
        return userToRoomDAO.deleteUserFromRoom(userToRoom);
    }

    public List<UserToRoom> getAllUserToRooms(Integer roomId) {
        return userToRoomDAO.selectUsersByRoomId(roomId);
    }

    /**
     * 检查给定的用户是否已在指定的房间中。
     * 
     * @param username 用户名
     * @param roomId   房间ID
     * @return 如果用户已在房间中，则返回1；否则返回0。
     */
    private Integer checkIfUserInRoom(String username, Integer roomId) {
        // 调用UserToRoomDAO接口中的方法来检查数据库中是否存在相应的记录
        return userToRoomDAO.existsByUsernameAndRoomId(username, roomId);
    }
}
