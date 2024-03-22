package com.mli.assetjdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAOJdbc implements UsersDAO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean authenticate(String Username, String password) throws EmptyResultDataAccessException {
        String sql = "SELECT COUNT(*) FROM Users WHERE name = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, Username, password);
        logger.info("UsersDaoJdbc, User認證 '{}'", Username);
        return count == 1;
    }
}
