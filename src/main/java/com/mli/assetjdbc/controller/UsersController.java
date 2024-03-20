package com.mli.assetjdbc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.mli.assetjdbc.dto.LoginDTO;
import com.mli.assetjdbc.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/Users")
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersService UsersService;

    @Operation(summary = "用户登入")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String Username = loginDTO.getName();
        String password = loginDTO.getPassword();
        logger.info("User login attempt: {}", Username);

        try {
            boolean isAuthenticated = UsersService.authenticate(Username, password);
            if (isAuthenticated) {
                return new ResponseEntity<>("登入成功", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("帳號或密碼錯誤", HttpStatus.UNAUTHORIZED);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error during User login: {}", e.getMessage());
            return new ResponseEntity<>("登入中出錯", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
