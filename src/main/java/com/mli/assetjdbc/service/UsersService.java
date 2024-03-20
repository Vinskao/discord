package com.mli.assetjdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mli.assetjdbc.dao.UsersDaoJdbc;

public class UsersService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersDaoJdbc UsersDaoJdbc;
    
    public boolean authenticate(String username, String password) {
        logger.info("service, Authenticating user: {}", username);

        try {
            return UsersDaoJdbc.authenticate(username, password);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
