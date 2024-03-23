package com.mli.assetjdbc.dao;

import java.util.List;

import com.mli.assetjdbc.model.Users;

public interface UsersDAO {
    boolean authenticate(int id, String password);

    List<Users> findById(int userId);
}
