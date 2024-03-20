package com.mli.assetjdbc.dao;

public interface UsersDao {
    boolean authenticate(String Username, String password);
}
