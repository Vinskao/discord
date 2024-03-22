package com.mli.assetjdbc.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 * 用户
 * 
 * @version 1.0
 * @author D3031104
 */
@Schema(description = "用戶")
public class Users {
    /** 用户ID */
    @Schema(hidden = true)
    private int id;

    /** 用户名 */
    @Schema(description = "用戶名稱", name = "名稱")
    private String name;

    /** 入職日期 */
    @Schema(description = "用戶入職日期", name = "入職日期")
    private LocalDate hireDate;

    /** 是否活躍 */
    @Schema(description = "用戶是否活躍", name = "是否活躍")
    private boolean isActive;

    /** 是否管理员 */
    @Schema(description = "用戶是否是管理员", name = "是否管理员")
    private boolean isAdmin;

    /** 離職日期 */
    @Schema(description = "用戶離職日期", name = "離職日期")
    private LocalDate resignationDate;

    /** 密碼 */
    @Schema(description = "用戶密碼", name = "密碼")
    private String password;

    /** 部門ID */
    @Schema(description = "用戶所屬部門ID", name = "部門ID")
    private int unitId;

    public Users() {
    }

    public Users(int id, String name, LocalDate hireDate, boolean isActive, boolean isAdmin, LocalDate resignationDate,
            String password, int unitId) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
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

    public boolean isIsAdmin() {
        return this.isAdmin;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    public Users isAdmin(boolean isAdmin) {
        setIsAdmin(isAdmin);
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
        return id == users.id && Objects.equals(name, users.name) && Objects.equals(hireDate, users.hireDate)
                && isActive == users.isActive && isAdmin == users.isAdmin
                && Objects.equals(resignationDate, users.resignationDate) && Objects.equals(password, users.password)
                && unitId == users.unitId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hireDate, isActive, isAdmin, resignationDate, password, unitId);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", hireDate='" + getHireDate() + "'" +
                ", isActive='" + isIsActive() + "'" +
                ", isAdmin='" + isIsAdmin() + "'" +
                ", resignationDate='" + getResignationDate() + "'" +
                ", password='" + getPassword() + "'" +
                ", unitId='" + getUnitId() + "'" +
                "}";
    }

}
