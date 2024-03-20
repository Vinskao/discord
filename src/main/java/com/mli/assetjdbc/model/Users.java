package com.mli.assetjdbc.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema
public class Users {
    @Schema(hidden = true)
    private int id;

    @Schema(description = "The User's name")
    private String name;

    @Schema(description = "The date when the User was hired")
    private LocalDate hireDate;

    @Schema(description = "Whether the User is currently active or not")
    private boolean isActive;

    @Schema(description = "The date when the User resigned, if applicable")
    private LocalDate resignationDate;

    @Schema(description = "The User's password")
    private String password;

    public Users() {
    }

    public Users(int id, String name, LocalDate hireDate, boolean isActive, LocalDate resignationDate,
            String password) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.isActive = isActive;
        this.resignationDate = resignationDate;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isIsActive() {
        return this.isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getResignationDate() {
        return this.resignationDate;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users id(int id) {
        setId(id);
        return this;
    }

    public Users name(String name) {
        setName(name);
        return this;
    }

    public Users hireDate(LocalDate hireDate) {
        setHireDate(hireDate);
        return this;
    }

    public Users isActive(boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public Users resignationDate(LocalDate resignationDate) {
        setResignationDate(resignationDate);
        return this;
    }

    public Users password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Users)) {
            return false;
        }
        Users User = (Users) o;
        return id == User.id && Objects.equals(name, User.name) && Objects.equals(hireDate, User.hireDate)
                && isActive == User.isActive && Objects.equals(resignationDate, User.resignationDate)
                && Objects.equals(password, User.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hireDate, isActive, resignationDate, password);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", hireDate='" + getHireDate() + "'" +
                ", isActive='" + isIsActive() + "'" +
                ", resignationDate='" + getResignationDate() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

}