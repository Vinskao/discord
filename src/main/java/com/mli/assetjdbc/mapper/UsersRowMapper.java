package com.mli.assetjdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;
import com.mli.assetjdbc.model.Users;

/**
 * 
 * @Author D3031104
 * @version 1.0
 *          用戶行映射器
 */
public class UsersRowMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users users = new Users();
		users.setId(rs.getInt("id"));
		users.setName(rs.getString("name"));
		users.setIsActive(rs.getBoolean("is_active"));
		users.setIsAdmin(rs.getBoolean("is_admin"));

		String hireDateStr = rs.getString("hire_date");
		LocalDate hireDate = LocalDate.parse(hireDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		users.setHireDate(hireDate);

		String resignDateStr = rs.getString("resignation_date");
		LocalDate resignDate = LocalDate.parse(resignDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		users.setResignationDate(resignDate);

		users.setUnitId(rs.getInt("unit_id"));
		users.setPassword(rs.getString("password"));
		return users;
	}
}
