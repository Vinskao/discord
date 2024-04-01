package com.mli.discord.module.login.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mli.discord.module.login.dao.UserDAO;
import com.mli.discord.module.login.model.User;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 使用者服務類，提供使用者相關操作的方法。
 * 
 * @version 1.0
 * @author D3031104
 */
@Transactional
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    public User findByUsernameAndPassword(String username, String password) {
        logger.info("service, Authenticating User: {}", username);

        try {
            // 从数据库中获取用户存储的加密密码
            User user = userDAO.findEncodedPasswordByUsername(username);

            if (user != null) {
                // 从用户对象中获取存储的加密密码
                String encodedPassword = user.getPassword();

                // 使用PasswordEncoder.matches方法比较用户提交的密码和存储的加密密码
                boolean passwordMatches = passwordEncoder.matches(password, encodedPassword);

                if (passwordMatches) {
                    return user; // 返回用户对象，认证成功
                }
            }

        } catch (EmptyResultDataAccessException e) {
            logger.info("用户不存在", e.getMessage());
            return null;
        }
        return null;
    }

    /**
     * 根據使用者 ID 尋找使用者。
     * 
     * @param userId 使用者 ID
     * @return 匹配給定 ID 的使用者清單，如果找不到則返回空列表
     */
    @Operation(summary = "Find user by user ID")
    public List<User> findById(int userId) {
        logger.info("service, userId {}", userId);

        try {
            return userDAO.findById(userId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("service, User with id {} not found", userId);
            return null;
        }
    }

    @Operation(summary = "更新密碼")
    public boolean updatePassword(String username, String newPassword) {
        logger.info("服務層，正在更新用戶 {} 的密碼", username);

        try {
            boolean updated = userDAO.updatePasswordByUsername(username, passwordEncoder.encode(newPassword)) > 0;
            if (updated) {
                logger.info("密碼更新成功");
            } else {
                logger.info("密碼更新失敗");
            }
            return updated;
        } catch (Exception e) {
            logger.error("更新密碼時出現異常: {}", e.getMessage());
            return false;
        }
    }
}
