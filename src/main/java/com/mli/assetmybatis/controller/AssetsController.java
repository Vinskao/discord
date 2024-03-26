package com.mli.assetmybatis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mli.assetmybatis.dao.AssetsDAO;
import com.mli.assetmybatis.dto.AssetRequestDTO;
import com.mli.assetmybatis.dto.AssetsTotalDTO;
import com.mli.assetmybatis.dto.UnitIdDTO;
import com.mli.assetmybatis.model.Assets;
import com.mli.assetmybatis.service.AssetsService;
import com.mli.assetmybatis.util.CSVReader;
import com.mli.assetmybatis.util.ExcelGenerator;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 控制器類，用於處理有關資產的端點。
 *
 * @Author D3031104
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/assets")
public class AssetsController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AssetsDAO assetsDAO;

	@Autowired
	private AssetsService assetsService;

	/**
	 * 從資料庫中擷取所有資產。
	 *
	 * @return 如果找到資產則回傳包含資產清單的 ResponseEntity，如果找不到則回傳 404 Not Found 狀態。
	 */
	@Operation(summary = "從資料庫中擷取所有資產")
	@PostMapping("/select-all")
	public ResponseEntity<?> getAllAssets() {
		List<Assets> assets = assetsService.getAllAssets();
		if (assets.isEmpty()) {
			return new ResponseEntity<>("資料庫中沒有資產", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(assets, HttpStatus.OK);
		}
	}

	/**
	 * 根據資產編號從資料庫中提取資產。
	 *
	 * @param requestDTO 資產請求資料。
	 * @return 如果找到資產則傳回包含資產資訊的 ResponseEntity，如果找不到則傳回 404 Not Found 狀態。
	 */
	@Operation(summary = "根據資產編號從資料庫中擷取資產")
	@PostMapping("/select-by-asset-number")
	public ResponseEntity<?> getAssetByAssetNumber(AssetRequestDTO requestDTO) {
		String assetNumber = requestDTO.getAssetNumber();
		logger.info("資產編號：{}", assetNumber);
		Assets asset = assetsService.getAssetByAssetNumber(assetNumber);
		if (asset == null) {
			String message = "Asset with number " + assetNumber + " not found.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(asset, HttpStatus.OK);
		}
	}

	/**
	 * 新增一筆資產到資料庫。
	 *
	 * @param asset 要新增的資產。
	 * @return 如果成功新增資產則回傳包含成功訊息的 ResponseEntity，如果資產為空則回傳 404 Not Found 狀態。
	 */
	@Operation(summary = "新增一筆資產")
	@PostMapping("/add")
	public ResponseEntity<String> addAsset(@RequestBody Assets asset) {
		logger.info("新增的資產：{}", asset);
		String assetNumber = asset.getAssetNumber();

		try {
			Assets temp = assetsService.getAssetByAssetNumber(assetNumber);
			if (temp == null) {
				assetsService.addAsset(asset);
				return new ResponseEntity<>("資產新增成功", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("已經有此編號了", HttpStatus.NOT_FOUND);
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error adding asset: {}", e.getMessage());
			return new ResponseEntity<>("新增資產時出現錯誤", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 更新資產。
	 *
	 * @param asset 要更新的資產。
	 * @return 如果成功更新資產則回傳包含成功訊息的 ResponseEntity，如果資產為空則回傳 400 Bad Request 狀態。
	 */
	@Operation(summary = "更新資產")
	@PostMapping("/update")
	public ResponseEntity<String> updateAsset(@RequestBody Assets asset) {
		logger.info("controller, asset = {}", asset);
		String assetNumber = asset.getAssetNumber();

		try {
			Assets temp = assetsService.getAssetByAssetNumber(assetNumber);

			if (temp != null) {
				assetsService.updateAsset(asset);
				;
				return new ResponseEntity<>("資產修改成功", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("沒有此編號", HttpStatus.NOT_FOUND);
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error adding asset: {}", e.getMessage());
			return new ResponseEntity<>("新增資產時出現錯誤", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 刪除資產。
	 *
	 * @param requestDTO 資產請求資料。
	 * @return 如果成功刪除資產則回傳包含成功訊息的 ResponseEntity，如果資產編號為空則回傳 400 Bad Request 狀態。
	 */
	@Operation(summary = "刪除資產")
	@PostMapping("/delete")
	public ResponseEntity<String> deleteAsset(@RequestBody(required = false) AssetRequestDTO requestDTO) {
		logger.info("controller, requestDTO = {}", requestDTO);
		String assetNumber = requestDTO.getAssetNumber();

		try {
			Assets temp = assetsService.getAssetByAssetNumber(assetNumber);

			if (temp != null) {
				assetsService.deleteAsset(assetNumber);
				return new ResponseEntity<>("刪除成功", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("沒有此編號", HttpStatus.NOT_FOUND);
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error deleting asset: {}", e.getMessage());
			return new ResponseEntity<>("刪除資產時出現錯誤", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 根據部門編號查詢資產列表。
	 *
	 * @param unitIdDTO 包含部門編號的 DTO 物件。
	 * @return 符合部門編號的資產列表。
	 */
	@PostMapping("/select-by-unit-id")
	@Operation(summary = "根據部門編號查詢資產列表")
	public ResponseEntity<?> getAssetsByUnitId(@RequestBody UnitIdDTO unitIdDTO) {
		logger.info("controller, unitIdDTO = {}", unitIdDTO);

		try {
			List<Assets> assets = assetsService.getAssetsByUnitId(unitIdDTO.getUnitId());
			return new ResponseEntity<>(assets, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error fetching assets by unit id: {}", e.getMessage());
			return new ResponseEntity<>("使用unitId獲取資產時出錯：" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 根據部門編號查詢資產列表，多回傳一筆total總計。
	 *
	 * @param unitIdDTO 包含部門編號的 DTO 物件。
	 * @return 符合部門編號的資產列表和總計。
	 */
	@PostMapping("/select-by-unit-id/total")
	@Operation(summary = "根據部門編號查詢資產列表，多回傳一筆total總計")
	public ResponseEntity<?> getAssetsByUnitIdTotal(@RequestBody UnitIdDTO unitIdDTO) {
		logger.info("controller, unitIdDTO = {}", unitIdDTO);

		try {
			List<Assets> assets = assetsService.getAssetsByUnitId(unitIdDTO.getUnitId());
			AssetsTotalDTO assetsTotalDTO = new AssetsTotalDTO(assets);
			// 調用 ExcelGenerator 生成 Excel 文件
			byte[] excelBytes = ExcelGenerator.generateAssetExcel(assetsTotalDTO);
			// 將生成的 Excel 文件作為二進位數組回傳給前端
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("filename", "assets.xlsx");
			headers.setContentLength(excelBytes.length);

			return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error fetching assets by unit id: {}", e.getMessage());
			return new ResponseEntity<>("使用unitId獲取資產時出錯：" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 從 CSV 檔案中讀取資料並新增多筆資產到資料庫。
	 *
	 * @return 如果成功新增資產則回傳包含成功訊息的 ResponseEntity，如果新增資產時發生錯誤則回傳 400 Bad Request 狀態。
	 */
	@Operation(summary = "使用csv新增一筆資產")
	@PostMapping("/add-from-csv")
	public ResponseEntity<String> addAssetsFromCSV() {
		String filePath = "input/assets.csv";
		List<Assets> assetsList = CSVReader.readCSV(filePath);

		try {
			for (Assets asset : assetsList) {
				int newId = assetsDAO.generateNewId();
				asset.setId(newId);

				String assetNumber = asset.getAssetNumber();
				Assets temp = assetsService.getAssetByAssetNumber(assetNumber);
				if (temp == null) {
					assetsService.addAsset(asset);
				} else {
					logger.info("資產已存在於資料庫中，資產編號: {}", assetNumber);
					continue;
				}
			}
			return new ResponseEntity<>("資產新增成功", HttpStatus.CREATED);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error adding asset: {}", e.getMessage());
			return new ResponseEntity<>("新增資產時操作錯誤", HttpStatus.BAD_REQUEST);
		}
	}

}
