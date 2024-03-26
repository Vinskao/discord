package com.mli.assetmybatis.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 資產請求的 DTO
 * 這個類代表了資產請求的資料傳輸對象。
 * 
 * @version 1.0
 * @author D3031104
 */
@Schema
public class AssetRequestDTO {
	@Schema(description = "資產編號")
	private String assetNumber;

	public AssetRequestDTO() {
	}

	public AssetRequestDTO(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	public String getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetNumber == null) ? 0 : assetNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetRequestDTO other = (AssetRequestDTO) obj;
		if (assetNumber == null) {
			if (other.assetNumber != null)
				return false;
		} else if (!assetNumber.equals(other.assetNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssetRequestDTO [assetNumber=" + assetNumber + "]";
	}

}
