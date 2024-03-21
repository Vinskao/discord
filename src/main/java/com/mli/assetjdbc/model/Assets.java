package com.mli.assetjdbc.model;

import java.time.LocalDate;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "資產")
public class Assets {
    @Schema(hidden = true)
    private int id;

    @Schema(description = "The asset number")
    private String assetNumber;

    @Schema(description = "The asset name")
    private String assetName;

    @Schema(description = "The unit of use")
    private String unitOfUse;

    @Schema(description = "The User")
    private String User;

    @Schema(description = "The creation date")
    private LocalDate creationDate;

    @Schema(description = "The value")
    private double value;

    @Schema(description = "The ID of the unit")
    private int unitId;


    public Assets() {
    }

    public Assets(int id, String assetNumber, String assetName, String unitOfUse, String User, LocalDate creationDate, double value, int unitId) {
        this.id = id;
        this.assetNumber = assetNumber;
        this.assetName = assetName;
        this.unitOfUse = unitOfUse;
        this.User = User;
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

    public String getUnitOfUse() {
        return this.unitOfUse;
    }

    public void setUnitOfUse(String unitOfUse) {
        this.unitOfUse = unitOfUse;
    }

    public String getUser() {
        return this.User;
    }

    public void setUser(String User) {
        this.User = User;
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

    public Assets unitOfUse(String unitOfUse) {
        setUnitOfUse(unitOfUse);
        return this;
    }

    public Assets User(String User) {
        setUser(User);
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
        return id == assets.id && Objects.equals(assetNumber, assets.assetNumber) && Objects.equals(assetName, assets.assetName) && Objects.equals(unitOfUse, assets.unitOfUse) && Objects.equals(User, assets.User) && Objects.equals(creationDate, assets.creationDate) && value == assets.value && unitId == assets.unitId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assetNumber, assetName, unitOfUse, User, creationDate, value, unitId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", assetNumber='" + getAssetNumber() + "'" +
            ", assetName='" + getAssetName() + "'" +
            ", unitOfUse='" + getUnitOfUse() + "'" +
            ", User='" + getUser() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", value='" + getValue() + "'" +
            ", unitId='" + getUnitId() + "'" +
            "}";
    }

}
