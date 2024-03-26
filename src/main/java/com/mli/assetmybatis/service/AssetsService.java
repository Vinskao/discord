package com.mli.assetmybatis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mli.assetmybatis.dao.AssetsDAO;
import com.mli.assetmybatis.model.Assets;

import java.util.List;

/**
 * 這個類別實現了對資產（Assets）的業務邏輯操作。
 * 
 * @Author D3031104
 * @version 1.0
 */
@Service
@Transactional
public class AssetsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AssetsDAO assetsMapper;

    /**
     * 獲取所有資產列表。
     * 
     * @return 包含所有資產的列表
     */
    public List<Assets> getAllAssets() {
        return assetsMapper.findAll();
    }

    /**
     * 根據資產編號查詢資產。
     * 
     * @param assetNumber 資產編號
     * @return 對應的資產對象，如果找不到則返回 null
     */
    public Assets getAssetByAssetNumber(String assetNumber) {
        logger.info("service, assetNumber = {}", assetNumber);

        try {
            return assetsMapper.findByAssetNumber(assetNumber);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 新增一筆資產記錄。
     * 
     * @param asset 要新增的資產對象
     */
    public void addAsset(Assets asset) {
        logger.info("service, asset = {}", asset);

        assetsMapper.insert(asset);
    }

    /**
     * 更新資產資訊。
     * 
     * @param asset 要更新的資產對象
     */
    public void updateAsset(Assets asset) {
        logger.info("service, asset = {}", asset);

        assetsMapper.update(asset);
    }

    /**
     * 根據資產編號刪除資產記錄。
     * 
     * @param assetNumber 資產編號
     */
    public void deleteAsset(String assetNumber) {
        logger.info("service, assetNumber = {}", assetNumber);

        assetsMapper.delete(assetNumber);
    }

    /**
     * 根據部門編號查詢該部門擁有的資產列表。
     * 
     * @param unitId 部門編號
     * @return 對應部門的資產列表
     */
    public List<Assets> getAssetsByUnitId(int unitId) {
        logger.info("service, unitId = {}", unitId);

        return assetsMapper.selectByUnitId(unitId);
    }
}
