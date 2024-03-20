package com.mli.assetjdbc.dao;

public interface UsersDao {
    boolean authenticate(String username, String password);
}
