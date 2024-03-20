package com.mli.assetjdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mli.assetjdbc.dao.AssetsDaoJdbc;
import com.mli.assetjdbc.model.Assets;

import java.util.List;

@Service
public class AssetsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AssetsDaoJdbc assetsDaoJdbc;

    public List<Assets> getAllAssets() {
        return assetsDaoJdbc.selectAll();
    }

    public Assets getAssetByAssetNumber(String assetNumber) {
        try {
            return assetsDaoJdbc.select(assetNumber);
        } catch (EmptyResultDataAccessException e) {
            return null; 
        }
    }

    public void addAsset(Assets asset) {
    	logger.info("service, asset = {}", asset);

        assetsDaoJdbc.insert(asset);
    }

    public void updateAsset(Assets asset) {
        assetsDaoJdbc.update(asset);
    }

    public void deleteAsset(String assetNumber) {
    	logger.info("service, assetNumber = {}", assetNumber);

        assetsDaoJdbc.delete(assetNumber);
    }
}
