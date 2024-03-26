package com.mli.assetmybatis.dao;

import java.util.List;

import com.mli.assetmybatis.model.Users;

/**
 * Users DAO MyBatis Mapper
 * 
 * @Author D3031104
 * @version 1.0
 */
public interface UsersDAO {
    /**
     * Authenticate user with given id and password.
     * 
     * @param id       The user ID
     * @param password The user password
     * @return true if authentication successful, false otherwise
     */
    boolean authenticate(int id, String password);

    /**
     * Find users by their ID.
     * 
     * @param userId The user ID
     * @return A list of users with the specified ID
     */
    List<Users> findById(int userId);
}
