package com.mli.assetjdbc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "部門編號請求資料")
public class UnitIdDTO {
    @Schema(description = "部門編號", example = "1")
    private int unitId;

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public UnitIdDTO() {
    }

    public UnitIdDTO(int unitId) {
        this.unitId = unitId;
    }

    public UnitIdDTO unitId(int unitId) {
        setUnitId(unitId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnitIdDTO)) {
            return false;
        }
        UnitIdDTO unitIdDTO = (UnitIdDTO) o;
        return unitId == unitIdDTO.unitId;
    }

    @Override
    public String toString() {
        return "{" +
                " unitId='" + getUnitId() + "'" +
                "}";
    }

}