package com.mli.discord.module.grouping.dto;

public class GroupIdDTO {
    private int groupId;

    public GroupIdDTO() {
    }

    public GroupIdDTO(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public GroupIdDTO groupId(int groupId) {
        setGroupId(groupId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GroupIdDTO)) {
            return false;
        }
        GroupIdDTO groupIdDTO = (GroupIdDTO) o;
        return groupId == groupIdDTO.groupId;
    }

    @Override
    public String toString() {
        return "{" +
                " groupId='" + getGroupId() + "'" +
                "}";
    }

}
