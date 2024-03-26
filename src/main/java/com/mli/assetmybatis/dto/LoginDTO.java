package com.mli.assetmybatis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 * 
 * @version 1.0
 * @author D3031104
 */
@Schema
public class LoginDTO {
    @Schema(description = "The User's id")
    private int id;

    @Schema(description = "The User's password")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDTO id(int id) {
        setId(id);
        return this;
    }

    public LoginDTO password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LoginDTO)) {
            return false;
        }
        LoginDTO loginDTO = (LoginDTO) o;
        return id == loginDTO.id && Objects.equals(password, loginDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

}