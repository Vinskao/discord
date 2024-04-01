package com.mli.discord.module.message.dto;

import java.util.Objects;

/**
 * 
 * @Author D3031104
 * @version 1.0
 */
public class RoomIdDTO {
    private Integer roomId;

    public RoomIdDTO() {
    }

    public RoomIdDTO(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public RoomIdDTO roomId(Integer roomId) {
        setRoomId(roomId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoomIdDTO)) {
            return false;
        }
        RoomIdDTO roomIdDTO = (RoomIdDTO) o;
        return Objects.equals(roomId, roomIdDTO.roomId);
    }

    @Override
    public String toString() {
        return "{" +
                " roomId='" + getRoomId() + "'" +
                "}";
    }

}