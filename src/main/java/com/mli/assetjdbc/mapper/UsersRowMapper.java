package com.mli.assetjdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;
import com.mli.assetjdbc.model.Users;
/**
 * 用戶行映射器
 */
public class UsersRowMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users users = new Users();
		users.setId(rs.getInt("id"));
		users.setName(rs.getString("name"));

		String hireDateStr  = rs.getString("hire_date");
		LocalDate hireDate  = LocalDate.parse(hireDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		users.setHireDate(hireDate);
		users.setUnitId(rs.getInt("unit_id"));

		return users;
	}
}
