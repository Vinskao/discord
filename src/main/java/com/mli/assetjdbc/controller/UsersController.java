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

import com.mli.assetjdbc.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/users")
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersService usersService;
    
    @Operation(summary = "用户登入")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = requestDTO.getPassword();
        logger.info("User login attempt: {}", username);

        try {
            boolean isAuthenticated = usersService.authenticate(username, password);
            if (isAuthenticated) {
                return new ResponseEntity<>("登录成功", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("用户名或密码错误", HttpStatus.UNAUTHORIZED);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error during user login: {}", e.getMessage());
            return new ResponseEntity<>("登录过程中出现错误", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
