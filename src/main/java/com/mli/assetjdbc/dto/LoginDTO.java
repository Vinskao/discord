package com.mli.assetjdbc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema
public class LoginDTO {
    @Schema(description = "The User's name")
    private String name;

    @Schema(description = "The User's password")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDTO name(String name) {
        setName(name);
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
        return Objects.equals(name, loginDTO.name) && Objects.equals(password, loginDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

}