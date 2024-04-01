package com.mli.discord.module.login.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;

/**
 * 用户
 * 
 * @version 1.0
 * @author D3031104
 */
@Data
@Builder
@Schema(description = "用戶")
public class User {
    /** 用户ID */
    @Schema(hidden = true)
    private Integer id;
    /** 密碼 */
    @Schema(description = "用戶密碼")
    private String password;
    /** 用户名 */
    @Schema(description = "用戶名稱")
    private String username;
    /** 用户权限 */
    @Schema(description = "用戶權限")
    private String authority;

    public User() {
    }

    public User(Integer id, String password, String username, String authority) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.authority = authority;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User id(Integer id) {
        setId(id);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User authority(String authority) {
        setAuthority(authority);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(password, user.password)
                && Objects.equals(username, user.username) && Objects.equals(authority, user.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, username, authority);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", password='" + getPassword() + "'" +
                ", username='" + getUsername() + "'" +
                ", authority='" + getAuthority() + "'" +
                "}";
    }

}
