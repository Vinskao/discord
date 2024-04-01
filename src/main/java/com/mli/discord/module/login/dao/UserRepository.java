package com.mli.discord.module.login.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.mli.discord.module.login.model.Authority;
import com.mli.discord.module.login.model.User;

/**
 * 使用者資料庫存取類別，用於管理使用者資料的存取和操作。
 * 
 * 
 * @Author D3031104
 * @version 1.0
 */

@Repository
public class UserRepository {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 對使用者密碼進行加密處理。
     * 
     * @param users 需要加密密碼的使用者物件陣列
     */
    private void encodePasswords(User... users) {
        for (User user : users) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
    }

    /**
     * 初始化方法，用於在系統啟動時檢查是否已存在使用者資料，若不存在則初始化資料並插入資料庫。
     */
    @PostConstruct
    private void init() {
        if (userDAO.findById(1).isEmpty() && userDAO.findById(2).isEmpty() &&
                userDAO.findById(3).isEmpty()) {
            // 這裡寫死了三個使用者，密碼分別是 password1 - 3
            User user1 = new User(1, "password1", "chiaki@mli.com",
                    Authority.ADMIN.toString());
            User user2 = new User(2, "password2", "min@mli.com",
                    Authority.NORMAL.toString());
            User user3 = new User(3, "password3", "alice@mli.com",
                    Authority.NORMAL.toString());

            // 對密碼進行加密
            encodePasswords(user1, user2, user3);

            // 將使用者存入資料庫
            userDAO.insertUser(user1);
            userDAO.insertUser(user2);
            userDAO.insertUser(user3);
        }
    }

    /**
     * 將指定使用者插入資料庫。
     * 
     * @param user 需要插入的使用者物件
     */
    public void insert(User user) {
        encodePasswords(user);
        userDAO.insertUser(user);
    }

    /**
     * 根據使用者ID查詢並返回使用者物件。
     * 
     * @param userId 使用者ID
     * @return 查詢到的使用者物件
     */
    public User findById(int userId) {
        return userDAO.findById(userId).get(0);
    }
}
