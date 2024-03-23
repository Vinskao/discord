package com.mli.assetjdbc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mli.assetjdbc.dao.UsersDAO;
import com.mli.assetjdbc.model.Users;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 使用者服務類，提供使用者相關操作的方法。
 * 
 * @version 1.0
 * @author D3031104
 */
@Service
public class UsersService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersDAO UsersDaoJdbc;

    public boolean authenticate(int id, String password) {
        logger.info("service, Authenticating User: {}", id);

        try {
            return UsersDaoJdbc.authenticate(id, password);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    /**
     * 根據使用者 ID 尋找使用者。
     * 
     * @param userId 使用者 ID
     * @return 匹配給定 ID 的使用者清單，如果找不到則返回空列表
     */
    @Operation(description = "Find users by user ID")
    public List<Users> findById(int userId) {
        logger.info("service, userId {}", userId);

        try {
            return UsersDaoJdbc.findById(userId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("service, User with id {} not found", userId);
            return null;
        }
    }
}
