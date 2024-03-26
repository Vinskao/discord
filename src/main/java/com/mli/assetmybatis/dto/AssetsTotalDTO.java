package com.mli.assetmybatis.dto;

import com.mli.assetmybatis.model.Assets;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0
 * @author D3031104
 *         資產總額的 DTO
 *         這個類代表了資產總額的資料傳輸對象。
 */
public class AssetsTotalDTO {
    private List<Assets> assets;
    private double total;

    private void calculateTotalValue() {
        total = 0.0;
        for (Assets asset : assets) {
            total += asset.getValue();
        }
    }

    public double getTotal() {
        return total;
    }

    public AssetsTotalDTO(List<Assets> assets) {
        this.assets = assets;
        calculateTotalValue();
    }

    public List<Assets> getAssets() {
        return this.assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
        calculateTotalValue();
    }

    public AssetsTotalDTO assets(List<Assets> assets) {
        setAssets(assets);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AssetsTotalDTO)) {
            return false;
        }
        AssetsTotalDTO assetsTotalDTO = (AssetsTotalDTO) o;
        return Objects.equals(assets, assetsTotalDTO.assets) && total == assetsTotalDTO.total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assets, total);
    }

    @Override
    public String toString() {
        return "{" +
                " assets='" + getAssets() + "'" +
                ", total='" + getTotal() + "'" +
                "}";
    }
}
