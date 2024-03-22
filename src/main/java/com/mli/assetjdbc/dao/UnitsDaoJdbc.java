package com.mli.assetjdbc.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mli.assetjdbc.mapper.UnitsRowMapper;
import com.mli.assetjdbc.model.Units;

@Repository
public class UnitsDAOJdbc implements UnitsDAO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Units> findAll() {
        String sql = "SELECT * FROM units";
        return jdbcTemplate.query(sql, new UnitsRowMapper());
    }

    @Override
    public List<Units> findById(int id) {
        logger.info("dao, id = {}", id);

        String sql = "SELECT * FROM units WHERE id = ?";
        return jdbcTemplate.query(sql, new UnitsRowMapper(), id);
    }
}
