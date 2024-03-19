package com.mli.assetjdbc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import com.mli.assetjdbc.model.Assets;
import com.mli.assetjdbc.service.AssetsService;

import dto.AssetRequestDTO;

@RestController
@RequestMapping("/assets")
public class AssetsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AssetsService assetsService;
    /**
      * 從資料庫中擷取所有資產。
      *
      * @return 如果找到資產則回傳包含資產清單的 ResponseEntity，如果找不到則回傳 404 Not Found 狀態。
      */
    @Operation(summary = "從資料庫中擷取所有資產")
    @GetMapping("/select-all")
    public ResponseEntity<?> getAllAssets(){
        List<Assets> assets = assetsService.getAllAssets();
        if(assets.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assets, HttpStatus.OK);
        }
    }

    /**
      * 根據資產編號從資料庫中提取資產。
      *
      * @param assetNumber 資產編號
      * @return 如果找到資產則傳回包含資產資訊的 ResponseEntity，如果找不到則傳回 404 Not Found 狀態。
      */
    @Operation(summary = "根據資產編號從資料庫中擷取資產")
    @ApiResponse(responseCode = "200", description = "成功擷取資產",
	    content = { @Content(mediaType = "application/json",
	    schema = @Schema(implementation = AssetRequestDTO.class)) })
	@ApiResponse(responseCode = "404", description = "找不到資產")
    @PostMapping("/select-by-asset-number")
    public ResponseEntity<?> getAssetByAssetNumber(@RequestBody @Schema(description = "資產請求資料") AssetRequestDTO requestDTO) {
        String assetNumber = requestDTO.getAssetNumber();
    	logger.info("assetNumber = {}", assetNumber);
        Assets asset = assetsService.getAssetByAssetNumber(assetNumber);
        if (asset == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(asset, HttpStatus.OK);
        }
    }

    @Operation(summary = "新增資產")
    @PostMapping("/add")
    public ResponseEntity<String> addAsset(@RequestBody Assets asset) {
    	logger.info("controller, asset = {}", asset);
        assetsService.addAsset(asset);
        if (asset == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        	return new ResponseEntity<>("Asset added successfully", HttpStatus.CREATED);
        }
    }

    @Operation(summary = "更新資產")
    @PutMapping("/update")
    public ResponseEntity<String> updateAsset(@RequestBody Assets asset) {
    	logger.info("controller, asset = {}", asset);
        if (asset == null) {
            return new ResponseEntity<>("Asset is null", HttpStatus.BAD_REQUEST);
        }
        assetsService.updateAsset(asset);
        return new ResponseEntity<>("Asset updated successfully", HttpStatus.OK);
    }

    @Operation(summary = "刪除資產")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAsset(@RequestBody(required = false) AssetRequestDTO requestDTO) {
        String assetNumber = (requestDTO != null && requestDTO.getAssetNumber() != null) ? requestDTO.getAssetNumber() : null;
    	logger.info("controller, assetNumber = {}", assetNumber);

        if (assetNumber == null || assetNumber.isEmpty()) {
            return new ResponseEntity<>("Asset number is required", HttpStatus.BAD_REQUEST);
        }

        assetsService.deleteAsset(assetNumber);
        return new ResponseEntity<>("Asset deleted successfully", HttpStatus.OK);
    }
}
