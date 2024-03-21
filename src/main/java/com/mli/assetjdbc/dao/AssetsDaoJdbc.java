package com.mli.assetjdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mli.assetjdbc.mapper.AssetsRowMapper;
import com.mli.assetjdbc.model.Assets;

@Repository
public class AssetsDaoJdbc implements AssetsDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Assets> selectAll() {
        String sql = "SELECT * FROM assets";
        return jdbcTemplate.query(sql, new AssetsRowMapper());
    }

    @Override
    @Nullable
    public Assets select(String assetNumber) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM assets WHERE asset_number = ?";
        return jdbcTemplate.queryForObject(sql, new AssetsRowMapper(), assetNumber);
    }

    @Override
    public boolean insert(Assets asset) {
        logger.info("dao, asset = {}", asset);
        String sql = "INSERT INTO assets (asset_number, asset_name, unit_of_use, User, creation_date, value, unit_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, asset.getAssetNumber(), asset.getAssetName(), asset.getUnitOfUse(),
                asset.getUser(), asset.getCreationDate(), asset.getValue(), asset.getUnitId()) > 0;
    }

    @Override
    public boolean update(Assets asset) {
        logger.info("dao, asset = {}", asset);
        String sql = "UPDATE assets SET asset_name = ?, unit_of_use = ?, User = ?, creation_date = ?, value = ?, unit_id = ? WHERE asset_number = ?";
        return jdbcTemplate.update(sql, asset.getAssetName(), asset.getUnitOfUse(), asset.getUser(),
                asset.getCreationDate(), asset.getValue(), asset.getUnitId(), asset.getAssetNumber()) > 0;
    }

    @Override
    public boolean delete(String assetNumber) {
        logger.info("dao, assetNumber = {}", assetNumber);

        String sql = "DELETE FROM assets WHERE asset_number = ?";
        return jdbcTemplate.update(sql, assetNumber) > 0;
    }
    
    @Override
    public List<Assets> selectByUnitId(int unitId) {
    	logger.info("dao, unitId = {}", unitId);

    	String sql = "SELECT * FROM assets WHERE unit_id = ?";
        return jdbcTemplate.query(sql, new AssetsRowMapper(), unitId);
    }

}
