package com.mli.assetjdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;
import com.mli.assetjdbc.model.Assets;

/**
 * 資產行映射器
 * 
 * @Author D3031104
 * @version 1.0
 */
public class AssetsRowMapper implements RowMapper<Assets> {

    @Override
    public Assets mapRow(ResultSet rs, int rowNum) throws SQLException {
        Assets assets = new Assets();
        assets.setId(rs.getInt("id"));
        assets.setAssetNumber(rs.getString("asset_number"));
        assets.setAssetName(rs.getString("asset_name"));
        assets.setUserId(rs.getInt("user_id"));

        String creationDateStr = rs.getString("creation_date");
        LocalDate creationDate = LocalDate.parse(creationDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assets.setCreationDate(creationDate);

        assets.setValue(rs.getDouble("value"));
        assets.setUnitId(rs.getInt("unit_id"));

        return assets;
    }
}
