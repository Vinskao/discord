package com.mli.assetjdbc.dao;

public interface UsersDAO {
    boolean authenticate(String Username, String password);
}
