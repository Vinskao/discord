package com.mli.assetjdbc.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema(description = "用戶")
public class Users {
    @Schema(hidden = true)
    private int id;

    @Schema(description = "The User's name", name = "名稱")
    private String name;

    @Schema(description = "The date when the User was hired", name = "入職日期")
    private LocalDate hireDate;

    @Schema(description = "Whether the User is currently active or not", name = "是否活躍")
    private boolean isActive;

    @Schema(description = "The date when the User resigned, if applicable", name = "離職日期")
    private LocalDate resignationDate;

    @Schema(description = "The User's password", name = "密碼")
    private String password;

    @Schema(description = "The ID of the unit", name = "部門ID")
    private int unitId;


    public Users() {
    }

    public Users(int id, String name, LocalDate hireDate, boolean isActive, LocalDate resignationDate, String password, int unitId) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.isActive = isActive;
        this.resignationDate = resignationDate;
        this.password = password;
        this.unitId = unitId;
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

    public int getUnitId() {
        return this.unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
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

    public Users unitId(int unitId) {
        setUnitId(unitId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Users)) {
            return false;
        }
        Users users = (Users) o;
        return id == users.id && Objects.equals(name, users.name) && Objects.equals(hireDate, users.hireDate) && isActive == users.isActive && Objects.equals(resignationDate, users.resignationDate) && Objects.equals(password, users.password) && unitId == users.unitId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hireDate, isActive, resignationDate, password, unitId);
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
            ", unitId='" + getUnitId() + "'" +
            "}";
    }
    
}
