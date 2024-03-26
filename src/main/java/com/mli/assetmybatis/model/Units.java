package com.mli.assetmybatis.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 * 表示部門的模型類。
 * 
 * @author D3031104
 * @version 1.0
 */
@Schema
public class Units {
    @Schema(hidden = true)
    private int id;

    @Schema(description = "The unit's name")
    private String name;

    @Schema(description = "The date when the unit was created")
    private LocalDateTime creationDate;

    @Schema(description = "The date when the unit was closed, if applicable")
    private LocalDateTime closedDate;

    public Units() {
    }

    public Units(int id, String name, LocalDateTime creationDate, LocalDateTime closedDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.closedDate = closedDate;
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

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getClosedDate() {
        return this.closedDate;
    }

    public void setClosedDate(LocalDateTime closedDate) {
        this.closedDate = closedDate;
    }

    public Units id(int id) {
        setId(id);
        return this;
    }

    public Units name(String name) {
        setName(name);
        return this;
    }

    public Units creationDate(LocalDateTime creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public Units closedDate(LocalDateTime closedDate) {
        setClosedDate(closedDate);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Units)) {
            return false;
        }
        Units units = (Units) o;
        return id == units.id && Objects.equals(name, units.name) && Objects.equals(creationDate, units.creationDate)
                && Objects.equals(closedDate, units.closedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate, closedDate);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", creationDate='" + getCreationDate() + "'" +
                ", closedDate='" + getClosedDate() + "'" +
                "}";
    }

}
