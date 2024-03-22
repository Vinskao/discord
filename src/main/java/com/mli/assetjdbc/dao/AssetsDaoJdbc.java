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

/**
 * 
 * 資產 DAO JDBC 實現
 * 
 * @Author D3031104
 * @version 1.0
 */
@Repository
public class AssetsDAOJdbc implements AssetsDAO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 從資料庫中選擇所有資產。
     * 
     * @return 資產列表
     */
    @Override
    public List<Assets> selectAll() {
        String sql = "SELECT * FROM assets";
        return jdbcTemplate.query(sql, new AssetsRowMapper());
    }

    /**
     * 根據資產編號選擇資產。
     * 
     * @param assetNumber 資產編號
     * @return 資產
     * @throws EmptyResultDataAccessException 如果結果集為空，service會處理
     */
    @Override
    @Nullable
    public Assets select(String assetNumber) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM assets WHERE asset_number = ?";
        return jdbcTemplate.queryForObject(sql, new AssetsRowMapper(), assetNumber);
    }

    /**
     * 插入資產。
     * 
     * @param asset 資產
     * @return 如果插入成功則返回 true，否則返回 false
     */
    @Override
    public boolean insert(Assets asset) {
        logger.info("dao, asset = {}", asset);
        String sql = "INSERT INTO assets (asset_number, asset_name, user_id, creation_date, value, unit_id) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, asset.getAssetNumber(), asset.getAssetName(), asset.getUserId(),
                asset.getCreationDate(), asset.getValue(), asset.getUnitId()) > 0;
    }

    /**
     * 更新資產。
     * 
     * @param asset 資產
     * @return 如果更新成功則返回 true，否則返回 false
     */
    @Override
    public boolean update(Assets asset) {
        logger.info("dao, asset = {}", asset);
        String sql = "UPDATE assets SET asset_name = ? , user_id = ?, creation_date = ?, value = ?, unit_id = ? WHERE asset_number = ?";
        return jdbcTemplate.update(sql, asset.getAssetName(), asset.getUserId(),
                asset.getCreationDate(), asset.getValue(), asset.getUnitId(), asset.getAssetNumber()) > 0;
    }

    /**
     * 刪除資產。
     * 
     * @param assetNumber 資產編號
     * @return 如果刪除成功則返回 true，否則返回 false
     */
    @Override
    public boolean delete(String assetNumber) {
        logger.info("dao, assetNumber = {}", assetNumber);

        String sql = "DELETE FROM assets WHERE asset_number = ?";
        return jdbcTemplate.update(sql, assetNumber) > 0;
    }

    /**
     * 根據部門ID選擇資產。
     * 
     * @param unitId 部門ID
     * @return 資產列表
     */
    @Override
    public List<Assets> selectByUnitId(int unitId) {
        logger.info("dao, unitId = {}", unitId);

        String sql = "SELECT * FROM assets WHERE unit_id = ?";
        return jdbcTemplate.query(sql, new AssetsRowMapper(), unitId);
    }

    /**
     * 從資料庫中獲取最後一筆資產的ID。
     *
     * @return 最後一筆資產的ID，如果資料庫中沒有資產則返回0。
     */
    @Override
    public int getLastIdFromDatabase() {
        int lastId = 0;
        String sql = "SELECT MAX(id) AS last_id FROM assets";

        try {
            // 查詢資料庫並獲取最大ID
            Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);

            // 如果查詢結果不為空，將最大ID賦值給lastId
            if (maxId != null) {
                lastId = maxId;
            }
        } catch (EmptyResultDataAccessException e) {
            // 如果查詢結果為空，將最後ID設置為0
            lastId = 0;
        }

        return lastId;
    }

    /**
     * 生成新的資產ID。
     *
     * @return 新的資產ID
     */
    @Override
    public int generateNewId() {
        // 從資料庫中獲取最後一筆資產的ID
        int lastId = getLastIdFromDatabase();
        // 新的資產ID是最後一筆資產ID加1
        return lastId + 1;
    }
}
