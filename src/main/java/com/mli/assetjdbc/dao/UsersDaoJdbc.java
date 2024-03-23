package com.mli.assetjdbc.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mli.assetjdbc.mapper.UsersRowMapper;
import com.mli.assetjdbc.model.Users;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 使用 JDBC 實現的使用者資料存取物件。
 * 
 * @version 1.0
 * @author D3031104
 */
@Repository
public class UsersDAOJdbc implements UsersDAO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通過使用者名稱和密碼進行身份驗證。
     * 
     * @param Username 使用者名稱
     * @param password 密碼
     * @return 如果驗證成功，則返回 true，否則返回 false
     * @throws EmptyResultDataAccessException 如果使用者不存在或者驗證失敗
     */
    @Operation(description = "Authenticate user by username and password")
    @Override
    public boolean authenticate(int id, String password) throws EmptyResultDataAccessException {
        String sql = "SELECT COUNT(*) FROM Users WHERE id = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id, password);
        logger.info("UsersDaoJdbc, 使用者認證 '{}'", id);
        return count == 1;
    }

    /**
     * 根據使用者 ID 尋找使用者。
     * 
     * @param userId 使用者 ID
     * @return 匹配給定 ID 的使用者清單，如果找不到則返回空列表
     */
    @Operation(description = "Find users by user ID")
    @Override
    public List<Users> findById(int userId) {
        logger.info("DAO, 使用者 ID {}", userId);
        String sql = "SELECT * FROM Users WHERE id = ?";
        return jdbcTemplate.query(sql, new UsersRowMapper(), userId);
    }
}
