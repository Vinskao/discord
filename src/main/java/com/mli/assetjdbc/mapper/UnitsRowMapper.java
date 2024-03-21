package com.mli.assetjdbc.mapper;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import java.time.format.DateTimeFormatter;

import com.mli.assetjdbc.model.Units;

public class UnitsRowMapper implements RowMapper<Units> {
    @Override
    public Units mapRow(ResultSet rs, int rowNum) throws SQLException {
		Units units = new Units();
		units.setId(rs.getInt("id"));
		units.setName(rs.getString("name"));
	    
	    String creationDateStr = rs.getString("creation_date");
	    LocalDate creationDate = LocalDate.parse(creationDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    units.setCreationDate(creationDate);
	    
	    String closedDateStr = rs.getString("closed_date");
	    if (closedDateStr != null) {
	        LocalDate closedDate = LocalDate.parse(closedDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        units.setClosedDate(closedDate);
	    }
	    return units;
    }
}
