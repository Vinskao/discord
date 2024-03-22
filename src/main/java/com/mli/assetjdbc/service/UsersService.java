package com.mli.assetjdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mli.assetjdbc.dao.UsersDAO;

@Service
public class UsersService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersDAO UsersDaoJdbc;

    public boolean authenticate(String Username, String password) {
        logger.info("service, Authenticating User: {}", Username);

        try {
            return UsersDaoJdbc.authenticate(Username, password);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
