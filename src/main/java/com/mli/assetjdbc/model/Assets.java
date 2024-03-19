package com.mli.assetjdbc.model;


import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class Assets {
	@JsonProperty("id")
    @Schema(description = "id")
    private int id;
    
    @JsonProperty("asset_number")
    @Schema(description = "The asset number")
    private String assetNumber;
    
    @JsonProperty("asset_name")
    @Schema(description = "The asset name")
    private String assetName;
    
    @JsonProperty("unit_of_use")
    @Schema(description = "The unit of use")
    private String unitOfUse;
    
    @JsonProperty("user")
    @Schema(description = "The user")
    private String user;
    
    @JsonProperty("creation_date")
    @Schema(description = "The creation date")
    private LocalDate creationDate;
    
    @JsonProperty("value")
    @Schema(description = "The value")
    private double value;

    public Assets() {
    }

    public Assets(int id, String assetNumber, String assetName, String unitOfUse, String user, LocalDate creationDate, double value) {
        this.id = id;
        this.assetNumber = assetNumber;
        this.assetName = assetName;
        this.unitOfUse = unitOfUse;
        this.user = user;
        this.creationDate = creationDate;
        this.value = value;
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
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public Assets user(String user) {
        setUser(user);
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Assets)) {
            return false;
        }
        Assets assets = (Assets) o;
        return id == assets.id && Objects.equals(assetNumber, assets.assetNumber) && Objects.equals(assetName, assets.assetName) && Objects.equals(unitOfUse, assets.unitOfUse) && Objects.equals(user, assets.user) && Objects.equals(creationDate, assets.creationDate) && value == assets.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assetNumber, assetName, unitOfUse, user, creationDate, value);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", assetNumber='" + getAssetNumber() + "'" +
            ", assetName='" + getAssetName() + "'" +
            ", unitOfUse='" + getUnitOfUse() + "'" +
            ", user='" + getUser() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }

}
