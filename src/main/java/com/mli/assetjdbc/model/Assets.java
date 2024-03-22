package com.mli.assetjdbc.model;

import java.time.LocalDate;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @Author D3031104
 * @version 1.0
 */
@Schema(description = "資產")
public class Assets {
    @Schema(hidden = true)
    private int id;

    @Schema(description = "The asset number")
    private String assetNumber;

    @Schema(description = "The asset name")
    private String assetName;

    @Schema(description = "The User'd id")
    private int userId;

    @Schema(description = "The creation date")
    private LocalDate creationDate;

    @Schema(description = "The value")
    private double value;

    @Schema(description = "The ID of the unit")
    private int unitId;

    public Assets() {
    }

    public Assets(int id, String assetNumber, String assetName, int userId, LocalDate creationDate, double value,
            int unitId) {
        this.id = id;
        this.assetNumber = assetNumber;
        this.assetName = assetName;
        this.userId = userId;
        this.creationDate = creationDate;
        this.value = value;
        this.unitId = unitId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetNumber() {
        return this.assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getUnitId() {
        return this.unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public Assets id(int id) {
        setId(id);
        return this;
    }

    public Assets assetNumber(String assetNumber) {
        setAssetNumber(assetNumber);
        return this;
    }

    public Assets assetName(String assetName) {
        setAssetName(assetName);
        return this;
    }

    public Assets userId(int userId) {
        setUserId(userId);
        return this;
    }

    public Assets creationDate(LocalDate creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public Assets value(double value) {
        setValue(value);
        return this;
    }

    public Assets unitId(int unitId) {
        setUnitId(unitId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Assets)) {
            return false;
        }
        Assets assets = (Assets) o;
        return id == assets.id && Objects.equals(assetNumber, assets.assetNumber)
                && Objects.equals(assetName, assets.assetName) && userId == assets.userId
                && Objects.equals(creationDate, assets.creationDate) && value == assets.value
                && unitId == assets.unitId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assetNumber, assetName, userId, creationDate, value, unitId);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", assetNumber='" + getAssetNumber() + "'" +
                ", assetName='" + getAssetName() + "'" +
                ", userId='" + getUserId() + "'" +
                ", creationDate='" + getCreationDate() + "'" +
                ", value='" + getValue() + "'" +
                ", unitId='" + getUnitId() + "'" +
                "}";
    }
}
